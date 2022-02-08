import React from 'react';
import { Link } from 'react-router-dom';

const NotFound = () =>
  <div className="NotFound">
      <h1>Не найдено</h1>
      <h3>Такой страницы не существует!</h3>
      <div>
         Попробуйте поискать на этих страницах:
      </div>
      <ul>
          <li><Link to="/">Главная страница</Link></li>
      </ul>
  </div>;

export default NotFound;
