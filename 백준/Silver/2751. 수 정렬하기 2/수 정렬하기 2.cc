#include <iostream>
#include <queue>
#include <vector>
#include <functional>    // greater, less
using namespace std;
int main() {
    priority_queue<int, vector<int>, greater<int>> pq;  // - >  priority_queue<int, vector<int>, less<int>> pq;
    int t, tmp;
    
    cin >> t;
 
    while(t--){
    cin >> tmp;
    pq.push(tmp);
    }
    
    while (!pq.empty()) {
        cout << pq.top() << '\n';
        pq.pop();
    }

    return 0;
}
