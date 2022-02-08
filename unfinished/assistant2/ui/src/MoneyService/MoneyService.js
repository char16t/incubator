import axios from 'axios';

class MoneyService {
  static context = 'api/v1/money';

  createTransaction(apiKey, {
    date,
    category,
    payee,
    comment,
    outcomeAccount,
    outcomeAmount,
    outcomeCurrency,
    incomeAccount,
    incomeAmount,
    incomeCurrency,
    status
  }) {
    return this._securedRequest(apiKey, {
      method: 'POST',
      url: `${MoneyService.context}/transaction`,
      data: {
        id: "",
        user_id: "",
        date,
        category,
        payee,
        comment,
        outcomeAccount,
        outcomeAmount,
        outcomeCurrency,
        incomeAccount,
        incomeAmount,
        incomeCurrency,
        status
      }
    });
  }

  _securedRequest(apiKey, config) {
    return axios.request({
      headers: {
        Authorization: `Bearer ${apiKey}`
      },
      ...config
    });
  }
}

export default MoneyService;
