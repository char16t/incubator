const deep = (state = [], action) => {
    switch (action.type) {
      case 'DEEP':
        return [
          ...state,
          {
            id: action.id,
            text: action.text,
          }
        ]
      default:
        return state
    }
  }
  
  export default deep