import configureStore from 'redux-mock-store';

import { getDefaultMiddleware } from '@reduxjs/toolkit';

import {
  clearUserForm,
  createAccount,
} from './slice';

import { userFixture } from '../fixtures/user';

const mockStore = configureStore(getDefaultMiddleware());

jest.mock('../api/accounts');

describe('slice', () => {
  let store;

  beforeEach(() => {
    store = mockStore(() => ({
      userForm: userFixture[0],
    }));
  });

  describe('updateUserForm', () => {
    context('when successfully updated', () => {
      it('runs success', async () => {
        await store.dispatch(createAccount());

        const actions = store.getActions();

        expect(clearUserForm.match(actions[0])).toBe(true);
      });
    });
  });
});
