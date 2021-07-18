import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  account: {},
  ledgers: [],
  accountCreateform: {
    email: '',
    name: '',
  },
};

const { actions, reducer } = createSlice({
  name: 'app',
  initialState,
  reducers: {

  },
});

export const {
  todo,
} = actions;

export default reducer;
