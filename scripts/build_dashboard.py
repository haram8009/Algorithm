#!/usr/bin/env python3
"""Build the static algorithm dashboard dataset from solution folders and NOTE.md files."""
from __future__ import annotations

import argparse
import html
import json
import re
import subprocess
import sys
import unicodedata
from collections import OrderedDict
from pathlib import Path
from urllib.parse import quote, urlsplit, urlunsplit

ROOT = Path(__file__).resolve().parents[1]
SITE = ROOT / "site"
REPO = "haram8009/Algorithm"
CODE_EXTENSIONS = {".java", ".py", ".js", ".ts", ".cpp", ".cc", ".c", ".cs", ".go", ".sql", ".kt", ".swift"}

def slug(value: str) -> str:
    value = unicodedata.normalize("NFKD", str(value)).casefold()
    value = "".join(ch for ch in value if not unicodedata.combining(ch))
    return re.sub(r"[^a-z0-9]+", "-", value).strip("-")

def parse_scalar(value: str):
    value = value.strip()
    if not value:
        return ""
    try:
        return json.loads(value)
    except json.JSONDecodeError:
        if value.lower() in {"true", "false"}:
            return value.lower() == "true"
        if value.lower() in {"null", "~"}:
            return None
        try:
            return int(value)
        except ValueError:
            try:
                return float(value)
            except ValueError:
                return value.strip("'\"")

def read_note(path: Path) -> dict:
    text = path.read_text(encoding="utf-8")
    lines = text.splitlines()
    if not lines or lines[0].strip() != "---":
        raise ValueError(f"{path.relative_to(ROOT)}: NOTE.md must start with YAML frontmatter")
    end = next((i for i in range(1, len(lines)) if lines[i].strip() == "---"), None)
    if end is None:
        raise ValueError(f"{path.relative_to(ROOT)}: closing frontmatter delimiter is missing")
    values = {}
    for line in lines[1:end]:
        if not line.strip() or line.lstrip().startswith("#") or ":" not in line:
            continue
        key, value = line.split(":", 1)
        values[key.strip()] = parse_scalar(value)
    body = "\n".join(lines[end + 1:]).strip()
    insight_match = re.search(r"^##\s*한 줄 인사이트\s*$([\s\S]*?)(?=^##\s|\Z)", body, re.M)
    values["insight"] = insight_match.group(1).strip() if insight_match else str(values.get("insight") or "")
    values["_note_path"] = path.relative_to(ROOT).as_posix()
    return values

def first_added_dates() -> dict[str, str]:
    try:
        result = subprocess.run(
            ["git", "log", "--reverse", "--diff-filter=A", "--format=%as", "--name-only"],
            cwd=ROOT, check=True, capture_output=True, text=True, encoding="utf-8", errors="replace"
        )
    except (OSError, subprocess.CalledProcessError):
        return {}
    dates, current = {}, ""
    for line in result.stdout.splitlines():
        if re.fullmatch(r"\d{4}-\d{2}-\d{2}", line.strip()):
            current = line.strip()
        elif line.strip() and current:
            dates.setdefault(line.strip(), current)
    return dates

def platform_for(path: Path) -> str:
    first = path.parts[0] if path.parts else ""
    return {
        "백준": "백준",
        "프로그래머스": "프로그래머스",
        "Codetree": "Codetree",
        "SWEA": "SWEA",
        "Java": "LeetCode",
        "Python3": "LeetCode",
    }.get(first, "LeetCode" if re.match(r"^\d{3,}-", first) else "기타")

def clean_text(value: str) -> str:
    value = html.unescape(re.sub(r"<[^>]+>", " ", value))
    return re.sub(r"\s+", " ", value).strip()

def folder_title(folder: Path, platform: str) -> str:
    name = folder.name.replace("\u2005", " ").replace("\u3000", " ").strip()
    if platform == "프로그래머스":
        name = re.sub(r"^\d+\.\s*", "", name)
    elif platform == "백준":
        name = re.sub(r"^\d+\.\s*", "", name)
    elif platform == "LeetCode":
        name = re.sub(r"^\d+-", "", name)
    return name.replace("-", " ").strip() or folder.as_posix()

def read_problem_info(folder: Path, platform: str) -> tuple[str, str, str]:
    readme = folder / "README.md"
    text = readme.read_text(encoding="utf-8", errors="replace") if readme.is_file() else ""
    title = ""
    if platform == "LeetCode":
        match = re.search(r"<h2[^>]*>\s*<a[^>]*>(.*?)</a>", text, re.I | re.S)
        if match:
            title = clean_text(match.group(1))
            title = re.sub(r"^\d+\.\s*", "", title)
    elif platform == "프로그래머스":
        match = re.search(r"^#\s*\[level\s+\d+\]\s*(.*?)\s*-\s*\d+", text, re.I | re.M)
        if match:
            title = clean_text(match.group(1))
    elif platform == "Codetree":
        match = re.search(r"^#\s+\[(.*?)\]\(", text, re.M)
        if match:
            title = clean_text(match.group(1))
            title = re.sub(r"^\[[^\]]+\]\s*", "", title)
    elif platform == "백준":
        match = re.search(r"^#\s*(?:\[.*?\]\s*)?(.+?)\s*$", text, re.M)
        if match:
            title = clean_text(match.group(1))
    elif platform == "SWEA":
        match = re.search(r"^#\s*(.+)$", text, re.M)
        if match:
            title = clean_text(match.group(1))
    if not title:
        title = folder_title(folder, platform)
    url_match = re.search(
        r"https?://(?:www\.)?(?:leetcode\.com/problems/[^)\s\"<>]+|"
        r"school\.programmers\.co\.kr/[^)\s\"<>]+|"
        r"www\.acmicpc\.net/problem/[^)\s\"<>]+|"
        r"www\.codetree\.ai/[^)\s\"<>]+|"
        r"swexpertacademy\.com/[^)\s\"<>]+)",
        text, re.I
    )
    url = html.unescape(url_match.group(0).rstrip(".,;")) if url_match else ""
    difficulty = ""
    if platform == "LeetCode":
        match = re.search(r"<h3[^>]*>\s*(Easy|Medium|Hard)\s*</h3>", text, re.I)
        if match:
            difficulty = match.group(1).title()
        if not difficulty:
            difficulty = next((part for part in folder.parts if part in {"Easy", "Medium", "Hard"}), "")
    elif platform == "프로그래머스":
        level_match = re.search(r"/([0-3])(?:/|$)", folder.as_posix())
        if level_match:
            difficulty = "Lv. " + level_match.group(1)
    elif platform == "Codetree":
        match = re.search(r"\|\s*난이도\s*\|\s*([^|]+)\|", text)
        if match:
            difficulty = clean_text(match.group(1))
    elif platform == "백준":
        difficulty = next((part for part in folder.parts if part in {
            "Bronze", "Silver", "Gold", "Platinum", "Diamond", "Ruby"
        }), "")
    return title, url, difficulty

def canonical_url(url: str) -> str:
    if not url:
        return ""
    parts = urlsplit(str(url).strip())
    host = parts.netloc.lower().removeprefix("www.")
    path = re.sub(r"/+", "/", parts.path).rstrip("/")
    return urlunsplit((parts.scheme.lower() or "https", host, path, "", ""))

def stable_key(platform: str, title: str, url: str, problem_id=None) -> str:
    normalized_url = canonical_url(url)
    if normalized_url:
        return platform + "|url|" + normalized_url
    if problem_id not in (None, ""):
        return platform + "|id|" + str(problem_id).strip()
    return platform + "|title|" + slug(title)

def source_url(path: str) -> str:
    return f"https://github.com/{REPO}/blob/main/{quote(path, safe='/')}"

def code_records() -> list[dict]:
    added = first_added_dates()
    grouped: OrderedDict[str, dict] = OrderedDict()
    for file in sorted(ROOT.rglob("*")):
        if not file.is_file() or file.suffix.lower() not in CODE_EXTENSIONS:
            continue
        relative = file.relative_to(ROOT)
        if any(part in {".git", "site", "records", "scripts"} for part in relative.parts):
            continue
        folder = file.parent
        platform = platform_for(relative)
        title, url, difficulty = read_problem_info(folder, platform)
        id_match = re.match(r"^(\d+)[.-]", folder.name)
        problem_id = id_match.group(1) if id_match else ""
        key = stable_key(platform, title, url, problem_id)
        record = grouped.get(key)
        if record is None:
            record = {
                "problem_key": key, "platform": platform, "title": title,
                "problem_id": problem_id, "problem_url": url, "difficulty": difficulty,
                "topics": [], "study_group": "", "result": "Accepted",
                "solved_at": added.get(relative.as_posix(), ""),
                "retry_needed": False, "retry_at": "", "language": [],
                "complexity": "", "time_minutes": None, "insight": "",
                "code_url": "", "code_files": [], "note_path": "", "note_url": "",
                "notion_page_url": "", "source_path": folder.relative_to(ROOT).as_posix()
            }
            grouped[key] = record
        else:
            if not record["solved_at"] or (added.get(relative.as_posix()) and added[relative.as_posix()] < record["solved_at"]):
                record["solved_at"] = added.get(relative.as_posix(), record["solved_at"])
        language = {
            ".java": "Java", ".py": "Python", ".sql": "SQL", ".js": "JavaScript",
            ".ts": "TypeScript", ".cpp": "C++", ".cc": "C++", ".c": "C",
            ".cs": "C#", ".go": "Go", ".kt": "Kotlin", ".swift": "Swift"
        }.get(file.suffix.lower(), file.suffix.lstrip("."))
        record["code_files"].append({
            "name": file.name,
            "url": source_url(relative.as_posix()),
            "language": language
        })
        if language not in record["language"]:
            record["language"].append(language)
        if not record["code_url"]:
            record["code_url"] = source_url(relative.as_posix())
        if not record["difficulty"] and difficulty:
            record["difficulty"] = difficulty
    return list(grouped.values())

def overlay_note(record: dict, note: dict) -> dict:
    mapping = {
        "platform": "platform", "title": "title", "problem_id": "problem_id",
        "problem_url": "problem_url", "difficulty": "difficulty", "topics": "topics",
        "study_group": "study_group", "result": "result", "solved_at": "solved_at",
        "retry_needed": "retry_needed", "retry_at": "retry_at", "language": "language",
        "complexity": "complexity", "time_minutes": "time_minutes",
        "code_url": "code_url", "notion_page_url": "notion_page_url"
    }
    for source, target in mapping.items():
        value = note.get(source)
        if value not in (None, "", []):
            record[target] = value
    record["insight"] = note.get("insight", "")
    record["note_path"] = note["_note_path"]
    record["note_url"] = source_url(note["_note_path"])
    if record.get("code_files"):
        record["code_url"] = record["code_files"][0]["url"]
        record["language"] = sorted(set(record.get("language", []) + [
            item["language"] for item in record["code_files"]
        ]))
    elif note.get("code_url"):
        record["code_url"] = note["code_url"]
    return record

def build_records() -> list[dict]:
    codes = code_records()
    notes = [read_note(path) for path in sorted(ROOT.rglob("NOTE.md"))
             if not any(part in {".git", "site"} for part in path.relative_to(ROOT).parts)]
    by_dir = {}
    by_url = {}
    by_id = {}
    for record in codes:
        by_dir[record["source_path"]] = record
        url_key = canonical_url(record.get("problem_url", ""))
        if url_key:
            by_url[(record["platform"], url_key)] = record
        if record.get("problem_id"):
            by_id[(record["platform"], str(record["problem_id"]))] = record
    used = set()
    records = codes[:]
    for note in notes:
        note_dir = str(Path(note["_note_path"]).parent.as_posix())
        platform = str(note.get("platform") or "기타")
        url = str(note.get("problem_url") or "")
        record = by_dir.get(note_dir)
        if record is None and canonical_url(url):
            record = by_url.get((platform, canonical_url(url)))
        if record is None and note.get("problem_id") not in (None, ""):
            record = by_id.get((platform, str(note["problem_id"])))
        if record is None:
            record = {
                "problem_key": stable_key(platform, str(note.get("title") or ""), url, note.get("problem_id")),
                "platform": platform, "title": str(note.get("title") or "미분류 기록"),
                "problem_id": note.get("problem_id", ""), "problem_url": url,
                "difficulty": "", "topics": [], "study_group": "", "result": "",
                "solved_at": "", "retry_needed": False, "retry_at": "",
                "language": [], "complexity": "", "time_minutes": None, "insight": "",
                "code_url": "", "code_files": [], "note_path": "", "note_url": "",
                "notion_page_url": "", "source_path": note_dir
            }
            records.append(record)
        record_id = id(record)
        if record_id in used:
            raise ValueError(f"Multiple NOTE.md files matched one problem: {note['_note_path']}")
        used.add(record_id)
        overlay_note(record, note)
    for record in records:
        record["title"] = str(record.get("title") or "이름 없는 문제")
        if not record.get("result"):
            record["status"] = "풀이 완료" if record.get("code_files") or record.get("code_url") else ("풀이 기록" if record.get("solved_at") else "예정")
        else:
            record["status"] = str(record["result"])
        record["planned"] = (record.get("result") in {"못 품", "Wrong Answer", "TLE", "해설 봄", "예정"}) or (not record.get("code_files") and not record.get("code_url") and not record.get("solved_at") and not record.get("result"))
        record["topics"] = record.get("topics") if isinstance(record.get("topics"), list) else []
        record["language"] = record.get("language") if isinstance(record.get("language"), list) else []
        record["code_files"] = record.get("code_files") or []
        if not record.get("note_url") and record.get("note_path"):
            record["note_url"] = source_url(record["note_path"])
    unique = {}
    for record in records:
        key = record.get("problem_key") or stable_key(
            record["platform"], record["title"], record.get("problem_url", ""), record.get("problem_id")
        )
        if key in unique:
            current = unique[key]
            current["code_files"] = current.get("code_files", []) + record.get("code_files", [])
            current["code_url"] = current.get("code_url") or record.get("code_url", "")
            continue
        record["problem_key"] = key
        unique[key] = record
    return sorted(unique.values(), key=lambda item: (
        item.get("solved_at") or "0000-00-00", item.get("title", "").casefold()
    ), reverse=True)

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("--verify", action="store_true", help="validate generated dashboard data")
    args = parser.parse_args()
    records = build_records()
    notes = [path for path in ROOT.rglob("NOTE.md")
             if not any(part in {".git", "site"} for part in path.relative_to(ROOT).parts)]
    if args.verify:
        keys = [record["problem_key"] for record in records]
        if len(keys) != len(set(keys)):
            raise SystemExit("Duplicate problem keys found")
        if len(notes) < 44:
            raise SystemExit(f"Expected the 44 migrated notes; found {len(notes)}")
        if not records:
            raise SystemExit("No dashboard records were generated")
        for record in records:
            if not record.get("title"):
                raise SystemExit("A record has no title")
        print(f"Verified {len(records)} problems and {len(notes)} notes.")
        return
    target = SITE / "data" / "problems.json"
    target.parent.mkdir(parents=True, exist_ok=True)
    target.write_text(json.dumps(records, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    print(f"Generated {len(records)} problems from {len(notes)} NOTE.md files.")

if __name__ == "__main__":
    main()
