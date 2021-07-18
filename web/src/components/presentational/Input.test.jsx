import { render, fireEvent } from '@testing-library/react';

import Input from './Input';

const handleChange = jest.fn();

describe('Input', () => {
  const label = '이메일';
  const id = 'email';
  const value = 'tester@example.com';
  const placeholder = '이메일을 입력해주세요';

  const renderInput = () => render((
    <Input
      id={id}
      label={label}
      placeholder={placeholder}
      value={value}
      onChange={handleChange}
    />
  ));

  beforeEach(() => {
    jest.clearAllMocks();
  });

  it('renders Input', () => {
    const {
      getByLabelText,
      getByDisplayValue,
      getByPlaceholderText,
    } = renderInput();

    expect(getByLabelText(label)).toBeInTheDocument();
    expect(getByDisplayValue(value)).toBeInTheDocument();
    expect(getByPlaceholderText(placeholder)).toBeInTheDocument();
  });

  describe('if changes input value', () => {
    it('triggers onchange handler', () => {
      const { getByLabelText } = renderInput();

      fireEvent.change(getByLabelText(label), {
        target: { value: '테스터' },
      });

      expect(handleChange).toBeCalledWith({
        name: id,
        value: '테스터',
      });
    });
  });
});
