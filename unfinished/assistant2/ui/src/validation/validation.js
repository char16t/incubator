const EMAIL_REGEXP = /^(([^<>()[\].,;:\s@"]+(\.[^<>()[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+.)+[^<>()[\].,;:\s@"]{2,})$/i;

export function validateEmail(email) {
  const errors = [];

  if (!EMAIL_REGEXP.test(email)) {
    errors.push('скорее всего, такого адреса электронной почты не существует');
  }

  return errors;
}

export function validatePassword(password) {
  const errors = [];

  if (password.length < 5) {
    errors.push('как минимум 5 символов!')
  }

  return errors;
}

export function validateLogin(login) {
  const errors = [];

  if (login.length < 3) {
    errors.push('как минимум 3 символа!');
  }

  return errors;
}
