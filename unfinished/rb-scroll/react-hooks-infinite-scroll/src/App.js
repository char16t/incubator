import React, { Component } from "react";
import List from "./List";
import List2 from "./List2";

class App extends Component {
  render() {
    return (
      <div className="container">
        <div className="row">
          {/* <div className="col-6 justify-content-center my-5">
            <List />
          </div> */}
          <div className="col-12 justify-content-center my-5">
            <List2 />
          </div>
        </div>
      </div>
    );
  }
}

export default App;
