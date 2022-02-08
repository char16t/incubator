import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import PropTypes from 'prop-types';
import PasswordService from '../PasswordService/PasswordService';
import { serviceProp } from '../utils/utils';

class RecoverLostPassword extends Component {
  constructor(props) {
    super(props);
    this.state = {
      values: {
        loginOrEmail: ''
      },
      touchedControls: {
        loginOrEmail: false
      },
      resetComplete: false
    };

    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async handleSubmit(event) {
    event.preventDefault();
    try {
      const { loginOrEmail } = this.state.values;
      await this.props.passwordService.claimPasswordReset({ loginOrEmail });
      this.setState({ resetComplete: true });
      this.props.notifySuccess('Запрос на сброс пароля отправлен.');
    } catch (error) {
      this.props.notifyError('Не удалось отправить запрос на сброс пароля!');
      console.error(error);
    }
  }

  handleValueChange(key, value) {
    this.setState(state => ({ ...state, values: { ...state.values, [key]: value } }));
  }

  handleBlur(inputName) {
    this.setState(state => ({ ...state, touchedControls: { ...state.touchedControls, [inputName]: true } }));
  }

  isValid() {
    return this.state.values.loginOrEmail.length > 0;
  }

  render() {
    return (
      this.state.resetComplete ? <Redirect to="/login" />
      : <div className="RecoverLostPassword">
          <form className="CommonForm" onSubmit={this.handleSubmit}>
            <input type="text" name="loginOrEmail" placeholder="Электронная почта или логин"
              onChange={({ target }) => this.handleValueChange('loginOrEmail', target.value)}
              onBlur={() => this.handleBlur('loginOrEmail')} />
            { this.state.touchedControls.loginOrEmail && this.state.values.loginOrEmail.length < 1 ? <p className="validation-message">логин или электронная почта обязателны к заполнению!</p> : null }
            <input type="submit" value="Сбросить пароль" className="button-primary" disabled={!this.isValid()} />
          </form>
        </div>
    );
  }
}

RecoverLostPassword.propTypes = {
  passwordService: serviceProp(PasswordService),
  notifyError: PropTypes.func.isRequired,
  notifySuccess: PropTypes.func.isRequired,
};

export default RecoverLostPassword;
