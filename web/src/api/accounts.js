import axios from 'axios';

export const fetchAccount = async (id) => {
  const { data } = await axios.get(`http://localhost:8080/accounts/${id}`);
  return data;
};

export const postAccount = async (account) => {
  try {
    const { data } = await axios
      .post('http://localhost:8080/accounts', account);
    return data;
  } catch (err) {
    if (err.response?.status === 400) {
      throw new Error('잘못된 요청입니다. 요청 파라미터를 확인해 주세요');
    }

    throw err;
  }
};
