import React from 'react';
import { NavLink } from 'react-router-dom';
import PropTypes from 'prop-types';

const NavBar = ({ isLoggedIn, logout, user }) =>
  <div className="NavBar">
    <NavLink className="NavBar__link" activeClassName="NavBar__link--active" to="/" exact>Ассистент</NavLink>
    { isLoggedIn && user ? <NavLink className="NavBar__link" activeClassName="NavBar__link--active" to="/profile">{ user.login }</NavLink>
      : <NavLink className="NavBar__link" activeClassName="NavBar__link--active" to="/join">Зарегистрироваться</NavLink> }
    { isLoggedIn ? <a className="NavBar__link" onClick={logout}>Выйти</a>
      : <NavLink className="NavBar__link" activeClassName="NavBar__link--active" to="/login">Войти</NavLink> }
  </div>;

NavBar.propTypes = {
  isLoggedIn: PropTypes.bool.isRequired,
  logout: PropTypes.func.isRequired,
  user: PropTypes.shape({
    login: PropTypes.string.isRequired
  }),
};

export default NavBar;
