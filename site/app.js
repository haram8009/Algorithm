(() => {
  const state = { records: [], view: "table", month: new Date(new Date().getFullYear(), new Date().getMonth(), 1) };
  const $ = (s) => document.querySelector(s);
  const esc = (v) => String(v ?? "").replace(/[&<>"']/g, (c) => ({"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#39;"})[c]);
  const arr = (v) => Array.isArray(v) ? v : v ? [v] : [];
  const dateText = (v) => v ? String(v).slice(0, 10) : "—";

  function options(id, values) {
    const select = $("#" + id), first = select.options[0];
    select.replaceChildren(first);
    Array.from(new Set(values.filter(Boolean).map(String))).sort((a,b)=>a.localeCompare(b,"ko")).forEach((v)=>{
      const o=document.createElement("option"); o.value=v; o.textContent=v; select.append(o);
    });
  }
  function setup() {
    options("platform",state.records.map(x=>x.platform));
    options("difficulty",state.records.map(x=>x.difficulty));
    options("topic",state.records.flatMap(x=>arr(x.topics)));
    options("result",state.records.map(x=>x.result||x.status));
    options("language",state.records.flatMap(x=>arr(x.language)));
  }
  function filtered() {
    const q=$("#search").value.trim().toLocaleLowerCase("ko");
    let data=state.records.filter(x=>{
      const hay=[x.title,x.platform,x.problem_id,x.difficulty,x.result,x.status,x.complexity,x.insight,x.problem_url,...arr(x.topics),...arr(x.language)].join(" ").toLocaleLowerCase("ko");
      return (!q||hay.includes(q))&&(!$("#platform").value||x.platform===$("#platform").value)
        &&(!$("#difficulty").value||x.difficulty===$("#difficulty").value)
        &&(!$("#topic").value||arr(x.topics).includes($("#topic").value))
        &&(!$("#result").value||(x.result||x.status)===$("#result").value)
        &&(!$("#language").value||arr(x.language).includes($("#language").value))
        &&(!$("#retry-only").checked||x.retry_needed===true)
        &&(!$("#planned-only").checked||x.planned===true);
    });
    const sort=$("#sort").value;
    data.sort((a,b)=>sort==="title-asc"?a.title.localeCompare(b.title,"ko"):sort==="date-asc"?String(a.solved_at||"").localeCompare(String(b.solved_at||"")):String(b.solved_at||"").localeCompare(String(a.solved_at||"")));
    return data;
  }
  function topics(x) {
    const values=arr(x.topics);
    return values.length?'<div class="topic-list">'+values.slice(0,3).map(v=>'<span class="tag">'+esc(v)+'</span>').join("")+(values.length>3?'<span class="tag">+'+(values.length-3)+'</span>':"")+'</div>':"—";
  }
  function renderTable(data) {
    if(!data.length){$("#table-view").innerHTML='<div class="empty">조건에 맞는 문제가 없습니다.</div>';return;}
    const body=data.map(x=>{
      const result=x.result||x.status||"미기록";
      const dclass=String(x.difficulty||"").toLowerCase();
      const rclass=(result==="Accepted"||result==="풀이 완료")?"solved":(["못 품","Wrong Answer","TLE"].includes(result)?"retry":"");
      return '<tr data-id="'+esc(x.problem_key)+'"><td><span class="problem-title">'+esc(x.title)+'</span><span class="subline">'+esc(x.problem_id?"#"+x.problem_id:((x.code_files||[]).length||x.code_url?"코드 연결됨":x.planned?"미풀이 기록":"풀이 기록"))+'</span></td><td>'+esc(x.platform||"—")+'</td><td>'+(x.difficulty?'<span class="pill '+esc(dclass)+'">'+esc(x.difficulty)+'</span>':"—")+'</td><td>'+topics(x)+'</td><td><span class="pill '+rclass+'">'+esc(result)+'</span></td><td>'+esc(dateText(x.solved_at))+'</td><td>'+(x.retry_needed?'<span class="pill retry">다시 풀기</span>':"—")+'</td></tr>';
    }).join("");
    $("#table-view").innerHTML='<table><thead><tr><th>문제</th><th>플랫폼</th><th>난이도</th><th>유형</th><th>결과</th><th>푼 날짜</th><th>복습</th></tr></thead><tbody>'+body+'</tbody></table>';
  }
  function renderCalendar(data) {
    const y=state.month.getFullYear(),m=state.month.getMonth(),first=new Date(y,m,1),offset=(first.getDay()+6)%7;
    $("#month-label").textContent=new Intl.DateTimeFormat("ko-KR",{year:"numeric",month:"long"}).format(state.month);
    const cells=["월","화","수","목","금","토","일"].map(x=>'<div class="weekday">'+x+'</div>');
    const start=new Date(y,m,1-offset),today=new Date();
    for(let i=0;i<42;i++){
      const d=new Date(start.getFullYear(),start.getMonth(),start.getDate()+i);
      const iso=d.getFullYear()+"-"+String(d.getMonth()+1).padStart(2,"0")+"-"+String(d.getDate()).padStart(2,"0");
      const events=data.filter(x=>x.solved_at&&String(x.solved_at).slice(0,10)===iso).map(x=>{
        const status=x.result||x.status||"";
        const cls=(status==="못 품"||status==="Wrong Answer"||status==="TLE")?"failed":!x.code_files?.length&&!x.code_url&&!x.result?"pending":"";
        return '<button class="event '+cls+'" data-id="'+esc(x.problem_key)+'" title="'+esc(x.title)+'">'+esc(x.title)+'</button>';
      }).join("");
      const cls="day-cell"+(d.getMonth()!==m?" other-month":"")+(d.toDateString()===today.toDateString()?" today":"");
      cells.push('<div class="'+cls+'"><span class="day-number">'+d.getDate()+'</span><div class="event-list">'+events+'</div></div>');
    }
    $("#calendar-grid").innerHTML=cells.join("");
  }
  function render() {
    const data=filtered();
    $("#result-count").textContent=data.length.toLocaleString("ko-KR")+"개 기록";
    renderTable(data); renderCalendar(data);
  }
  function field(label,value) {
    let v=value;
    if(v===true)v="예"; else if(v===false)v="아니오";
    if(Array.isArray(v))v=v.join(", ");
    return '<div class="detail-field"><small>'+esc(label)+'</small><strong>'+esc(v||"—")+'</strong></div>';
  }
  function details(id) {
    const x=state.records.find(r=>r.problem_key===id); if(!x)return;
    let links=[];
    if(x.problem_url)links.push('<a href="'+esc(x.problem_url)+'" target="_blank" rel="noopener">문제 열기 ↗</a>');
    (x.code_files||[]).forEach(f=>links.push('<a class="secondary" href="'+esc(f.url)+'" target="_blank" rel="noopener">'+esc(f.language)+' 코드 ↗</a>'));
    if(!x.code_files?.length&&x.code_url)links.push('<a class="secondary" href="'+esc(x.code_url)+'" target="_blank" rel="noopener">코드 ↗</a>');
    if(x.note_url)links.push('<a class="secondary" href="'+esc(x.note_url)+'" target="_blank" rel="noopener">NOTE.md ↗</a>');
    $("#detail-content").innerHTML='<p class="eyebrow">'+esc(x.platform||"문제 기록")+'</p><h2 class="detail-title">'+esc(x.title)+'</h2><div class="detail-meta">'+esc(x.problem_id?"문제 번호 "+x.problem_id+" · ":"")+esc(x.difficulty||"난이도 미기록")+'</div><div class="detail-grid">'+field("유형",x.topics)+field("풀이 결과",x.result||x.status)+field("푼 날짜",dateText(x.solved_at))+field("다시 푼 날짜",dateText(x.retry_at))+field("재도전 필요",x.retry_needed)+field("언어",x.language)+field("풀이 시간",x.time_minutes?x.time_minutes+"분":"")+field("복잡도",x.complexity)+'</div>'+(x.insight?'<h3>한 줄 인사이트</h3><div class="detail-insight">'+esc(x.insight)+'</div>':"")+'<div class="detail-links">'+links.join("")+'</div>';
    $("#detail-dialog").showModal();
  }
  function view(name) {
    $("#table-view").hidden=name!=="table";$("#calendar-view").hidden=name!=="calendar";
    document.querySelectorAll(".view-button").forEach(b=>{const active=b.dataset.view===name;b.classList.toggle("active",active);b.setAttribute("aria-pressed",String(active));});
  }
  function bind() {
    ["search","platform","difficulty","topic","result","language","sort","retry-only","planned-only"].forEach(id=>$("#"+id).addEventListener("input",render));
    document.querySelectorAll(".view-button").forEach(b=>b.addEventListener("click",()=>view(b.dataset.view)));
    $("#reset").addEventListener("click",()=>{["search","platform","difficulty","topic","result","language"].forEach(id=>$("#"+id).value="");$("#sort").value="date-desc";$("#retry-only").checked=false;$("#planned-only").checked=false;render();});
    $("#prev-month").addEventListener("click",()=>{state.month=new Date(state.month.getFullYear(),state.month.getMonth()-1,1);render();});
    $("#next-month").addEventListener("click",()=>{state.month=new Date(state.month.getFullYear(),state.month.getMonth()+1,1);render();});
    $("#table-view").addEventListener("click",e=>{const row=e.target.closest("[data-id]");if(row)details(row.dataset.id);});
    $("#calendar-grid").addEventListener("click",e=>{const b=e.target.closest("[data-id]");if(b)details(b.dataset.id);});
  }
  async function start() {
    bind();
    try {
      const response=await fetch("./data/problems.json",{cache:"no-store"});
      if(!response.ok)throw new Error("대시보드 데이터를 불러오지 못했습니다 ("+response.status+")");
      state.records=await response.json();setup();
      $("#stat-total").textContent=state.records.length.toLocaleString("ko-KR");
      $("#stat-solved").textContent=state.records.filter(x=>x.code_files?.length||x.code_url||x.result==="Accepted").length.toLocaleString("ko-KR");
      $("#stat-retry").textContent=state.records.filter(x=>x.retry_needed).length.toLocaleString("ko-KR");
      $("#stat-planned").textContent=state.records.filter(x=>x.planned).length.toLocaleString("ko-KR");
      $("#load-state").textContent="";render();
    } catch(error) {
      $("#load-state").textContent=error.message;
      $("#result-count").textContent="데이터를 표시할 수 없습니다.";
      $("#table-view").innerHTML='<div class="empty">대시보드 데이터를 불러오지 못했습니다. 잠시 후 다시 열어주세요.</div>';
    }
  }
  start();
})();
