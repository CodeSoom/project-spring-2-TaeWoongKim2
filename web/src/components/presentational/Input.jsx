import React from 'react';

export default function Input({
  type = 'text',
  label,
  id,
  value,
  placeholder,
  onChange,
}) {
  const handleChange = ({ target }) => {
    onChange({
      name: target.id,
      value: target.value,
    });
  };

  return (
    <div>
      <label htmlFor={id}>{label}</label>
      <input
        type={type}
        id={id}
        value={value}
        placeholder={placeholder}
        onChange={handleChange}
      />
    </div>
  );
}
