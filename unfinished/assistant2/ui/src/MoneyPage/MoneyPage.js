import React, { Component } from 'react';
import MoneyService from '../MoneyService/MoneyService';

class MoneyPage extends Component {
  constructor(props) {
      super(props);
      this.handleSubmit = this.handleSubmit.bind(this);
  }

  async handleSubmit(event) {
    event.preventDefault();
    try {
      await MoneyService.createTransaction(
        this.props.apiKey,
        {
          date: new Date().toJSON(),
          category: 'Supermarkets',
          payee: 'Spar',
          comment: 'Spar',
          outcomeAccount: 'Alfabank',
          outcomeAmount: 724.29,
          outcomeCurrency: 'RUR',
          incomeAccount: null,
          incomeAmount: null,
          incomeCurrency: null,
          status: 'PAID'
        });
      this.props.notifySuccess('Личные данные изменены!');
    } catch (error) {
      this.props.notifyError('Не удалось изменить личные данные!');
      console.error(error);
    }
  }

  render() {
    return (
      <div>
        <h3>Money</h3>
        <p>Track money expences here</p>
        <form onSubmit={this.handleSubmit}>
          <input type="submit" value="Insert demo data" />
        </form>
      </div>
    );
  }
}

export default MoneyPage;
