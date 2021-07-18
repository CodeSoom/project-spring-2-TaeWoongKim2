import React from 'react';

import Input from '../presentational/Input';

export default function AccountContainer() {
  const handleChange = (value) => {
    const todo = '';
    return value + todo;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
  };

  return (
    <>
      <form onSubmit={handleSubmit}>
        <Input
          id="name"
          label="이름"
          placeholder="이름을 입력해주세요"
          value="name"
          onChange={handleChange}
        />
        <Input
          id="email"
          label="이메일"
          placeholder="이메일을 입력해주세요"
          value="email"
          onChange={handleChange}
        />
        <button type="submit">저장</button>
      </form>
    </>
  );
}
