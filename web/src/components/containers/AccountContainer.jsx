import React from 'react';

import { useDispatch, useSelector } from 'react-redux';

import AccountForm from '../presentational/AccountForm';

import {
  updateUserForm,
  createAccount,
} from '../../redux/slice';

export default function AccountContainer() {
  const { userForm } = useSelector((state) => state);

  const dispatch = useDispatch();

  const handleChange = (value) => {
    dispatch(updateUserForm(value));
  };

  const handleSubmit = () => {
    dispatch(createAccount());
  };

  return (
    <AccountForm
      account={userForm}
      onChange={handleChange}
      onSubmit={handleSubmit}
    />
  );
}
