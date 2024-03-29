import React from 'react';
import { shallow } from 'enzyme';
import PasswordDetails from './PasswordDetails';

const changePassword = jest.fn();
changePassword.mockReturnValue(Promise.resolve());

const notifyError = jest.fn();
const notifySuccess = jest.fn();

const userService = {
  changePassword
};

describe('structure', () => {
  it('should contain currentPassword input', () => {
    const wrapper = shallow(<PasswordDetails userService={userService} notifyError={notifyError} notifySuccess={notifySuccess} />);
    const currentPasswordInput = wrapper.find('input[name="currentPassword"]');
    expect(currentPasswordInput.length).toBe(1);
  });

  it('should contain newPassword input', () => {
    const wrapper = shallow(<PasswordDetails userService={userService} notifyError={notifyError} notifySuccess={notifySuccess} />);
    const newPasswordInput = wrapper.find('input[name="newPassword"]');
    expect(newPasswordInput.length).toBe(1);
  });

  it('should contain repeatedNewPassword input', () => {
    const wrapper = shallow(<PasswordDetails userService={userService} notifyError={notifyError} notifySuccess={notifySuccess} />);
    const repeatedNewPasswordInput = wrapper.find('input[name="repeatedNewPassword"]');
    expect(repeatedNewPasswordInput.length).toBe(1);
  });

  it('should contain update button', () => {
    const wrapper = shallow(<PasswordDetails userService={userService} notifyError={notifyError} notifySuccess={notifySuccess} />);
    const updateButton = wrapper.find('input[type="submit"]');
    expect(updateButton.length).toBe(1);
    expect(updateButton.props().disabled).toBe(true);
  });
});

describe('behaviour', () => {
  it('an error should appear under empty currentPassword input on blur', () => {
    const wrapper = shallow(<PasswordDetails userService={userService} notifyError={notifyError} notifySuccess={notifySuccess} />);
    const currentPasswordInput = wrapper.find('input[name="currentPassword"]');
    currentPasswordInput.simulate('blur');
    expect(wrapper.contains(<p className="validation-message" key={0}>необходимо ввести старый пароль!</p>)).toBe(true);
  });

  it('an error should appear under empty newPassword input on blur', () => {
    const wrapper = shallow(<PasswordDetails userService={userService} notifyError={notifyError} notifySuccess={notifySuccess} />);
    const newPasswordInput = wrapper.find('input[name="newPassword"]');
    newPasswordInput.simulate('blur');
    expect(wrapper.contains(<p className="validation-message" key={0}>как минимум 5 символов!</p>)).toBe(true);
  });

  it('an error should appear under empty repeatedNewPassword input on blur', () => {
    const wrapper = shallow(<PasswordDetails userService={userService} notifyError={notifyError} notifySuccess={notifySuccess} />);
    const repeatedNewPasswordInput = wrapper.find('input[name="repeatedNewPassword"]');
    repeatedNewPasswordInput.simulate('blur');
    expect(wrapper.contains(<p className="validation-message" key={0}>как минимум 5 символов!</p>)).toBe(true);
  });

  it('an error should appear under repeated password when passwords do not match and repeatedNewPassword input was touched', () => {
    const wrapper = shallow(<PasswordDetails userService={userService} notifyError={notifyError} notifySuccess={notifySuccess} />);
    const initialState = wrapper.state();
    wrapper.setState({ touchedControls: { ...initialState.touchedControls, repeatedNewPassword: true}, values: { ...initialState.values, newPassword: 'abcde', repeatedNewPassword: 'abcdefgh' } });
    expect(wrapper.contains(<p className="validation-message" key={0}>пароли не совпадают!</p>)).toBe(true);
  });

  it('should enable update button when all inputs are correct', () => {
    const wrapper = shallow(<PasswordDetails userService={userService} notifyError={notifyError} notifySuccess={notifySuccess} />);
    const initialState = wrapper.state();
    wrapper.setState({ ...initialState, values: { currentPassword: 'blabla', newPassword: 'P4sSW0Rd#1', repeatedNewPassword: 'P4sSW0Rd#1' } });
    const updateButton = wrapper.find('input[type="submit"]');
    expect(updateButton.props().disabled).toBe(false);
  });

  it('should call userService.changePassword fn when form is submitted', async () => {
    const wrapper = shallow(<PasswordDetails userService={userService} notifyError={notifyError} notifySuccess={notifySuccess} />);
    const initialState = wrapper.state();
    wrapper.setState({ ...initialState, values: { currentPassword: 'blabla', newPassword: 'P4sSW0Rd#1', repeatedNewPassword: 'P4sSW0Rd#1' } });
    const form = wrapper.find('form');
    await form.simulate('submit', { preventDefault: jest.fn() });
    expect(changePassword.mock.calls.length).toBe(1);
  });
});
