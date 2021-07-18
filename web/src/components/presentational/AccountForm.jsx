import Input from './Input';

export default function AccountForm({
  account,
  onChange,
  onSubmit,
  buttonText = '저장',
}) {
  const {
    email, name,
  } = account;

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit();
  };

  return (
    <form onSubmit={handleSubmit}>
      <Input
        id="email"
        label="이메일"
        placeholder="이메일을 입력해주세요"
        value={email}
        onChange={onChange}
      />
      <Input
        id="name"
        label="이름"
        placeholder="이름을 입력해주세요"
        value={name}
        onChange={onChange}
      />
      <button type="button" onClick={handleSubmit}>{buttonText}</button>
    </form>
  );
}
