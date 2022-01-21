import React from 'react'
import { connect } from 'react-redux'
import { add } from '../actions'
import deep from '../actions/deep'

const Add = ({ dispatch }) => {
  let input

  return (
    <div>
      <form
        onSubmit={e => {
          e.preventDefault()
          if (!input.value.trim()) {
            return
          }
          dispatch(add(0, input.value))
          dispatch(deep(1, input.value))
          input.value = ''
        }}
      >
        <input ref={node => (input = node)} />
        <button type="submit">Add</button>
      </form>
    </div>
  )
}

const mapStateToProps = (state, ownProps) => {
  console.error(state); 
  return {
    ...ownProps,
    state: state,
  }
};

export default connect(mapStateToProps)(Add)