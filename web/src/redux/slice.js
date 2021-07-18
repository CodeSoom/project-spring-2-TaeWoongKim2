import { createSlice } from '@reduxjs/toolkit';

import {
  postAccount,
} from '../api/accounts';

const initialState = {
  userForm: {
    email: '',
    name: '',
  },
};

const { actions, reducer } = createSlice({
  name: 'app',
  initialState,
  reducers: {
    updateUserForm: (state, { payload: { name, value } }) => ({
      ...state,
      userForm: {
        ...state.userForm,
        [name]: value,
      },
    }),
    clearUserForm: (state) => ({
      ...state,
      userForm: initialState.userForm,
    }),
  },
});

export const {
  updateUserForm,
  clearUserForm,
} = actions;

export const createAccount = () => async (dispatch, getState) => {
  const { userForm } = getState();

  try {
    await postAccount(userForm);
  } catch (err) {
    alert('계좌 생성이 실패했습니다.');
    return;
  }

  dispatch(clearUserForm());
  alert('계좌가 등록되었습니다.');
};

export default reducer;
