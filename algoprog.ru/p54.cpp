#include <iostream>
#include <string>
#include <cstdlib>

using namespace std;

int ssize = 0;
int data[512] = {0};

void push(int n)
{
  if (ssize < 512) {
    data[ssize] = n;
    ssize += 1;
  }
}

int pop()
{
  int v = 0;
  if (ssize > 0) {
    ssize -= 1;
    v = data[ssize];
  }
  return v;
}

int back()
{
  return data[ssize-1];
}

int size()
{
  return ssize;
}

void clear()
{
  ssize = 0;
}

int main()
{
  string cmd;
  while(true) {
    getline(cin, cmd);
    if (cmd == "exit") {
      cout << "bye" << endl;
      break;
    }
    else if (cmd.rfind("push", 0) == 0) {
      push(stoi(cmd.substr(5)));
      cout << "ok" << endl;
    }
    else if (cmd == "pop") {
      cout << pop() << endl;
    }
    else if (cmd == "back") {
      cout << back() << endl;
    }
    else if (cmd == "size") {
      cout << size() << endl;
    }
    else if (cmd == "clear") {
      clear();
      cout << "ok" << endl;
    }
  }
}
