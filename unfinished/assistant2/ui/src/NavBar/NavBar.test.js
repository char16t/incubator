import React from 'react';
import { NavLink } from 'react-router-dom';
import { shallow } from 'enzyme';
import NavBar from './NavBar';

describe('structure when the user is not logged in', () => {
  it('should contain 4 NavLinks', () => {
    const wrapper = shallow(<NavBar isLoggedIn={false} logout={jest.fn()} />);
    const navLinks = wrapper.find(NavLink);
    expect(navLinks.length).toBe(3);
    expect(navLinks.get(0).props.to).toBe('/');
    expect(navLinks.get(1).props.to).toBe('/join');
    expect(navLinks.get(2).props.to).toBe('/login');
  });
});

describe('structure when the user is logged in', () => {
  it('should contain 3 NavLinks and one anchor element', () => {
    const wrapper = shallow(<NavBar isLoggedIn={true} logout={jest.fn()} user={{ login: 'admin' }} />);
    const navLinks = wrapper.find(NavLink);
    expect(navLinks.length).toBe(2);
    expect(navLinks.get(0).props.to).toBe('/');
    expect(navLinks.get(1).props.to).toBe('/profile');
    expect(navLinks.get(1).props.children).toEqual('admin');
    expect(wrapper.find('a').get(0).props.children).toBe('Выйти');
  });
});
