import reducer, {
  updateUserForm,
  clearUserForm,
} from './slice';

import { userFixture } from '../fixtures/user';

describe('reducer', () => {
  const initialState = {
    userForm: {
      email: '',
      name: '',
    },
  };

  describe('updateUserForm', () => {
    const value = 'JSET Tester';

    it('updates form', () => {
      const state = reducer(initialState, updateUserForm({
        name: 'name',
        value,
      }));

      expect(state.userForm.name).toBe(value);
    });
  });

  describe('clearUserForm', () => {
    it('updates to initial form', () => {
      const state = reducer({
        ...initialState,
        userForm: {
          ...userFixture,
        },
      }, clearUserForm());

      expect(state.userForm).toEqual(initialState.userForm);
    });
  });
});
