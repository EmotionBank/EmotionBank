import { nicknameCheck } from '@/apis/user/nicknameCheck';
import { signupUser } from '@/apis/user/signupUser';
import { Button } from '@components/common/Button/Button';
import React, { useCallback, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

// type CheckboxType = {children: JSX.Element, isChecked:boolean}
// const Checkbox = ({children, isChecked}:CheckboxType) => {
//   const handleChecked = (isChecked:boolean) => {
//     isChecked = !isChecked
//   }
//   return(
//     <label>
//       <input 
//       type='checkbox'
//       checked={isChecked}
//       onChange={(target: {isChecked}) => handleChecked(isChecked)}
//       />
//       {children}
//     </label>
//   )
// }

const Signup = () => {
  let [index, setIndex] = useState(0);
  const navigate = useNavigate();
    const [ inputs, setInputs ] = useState(['','','',''])
  useEffect(() => {
    if (index === -1) {
      index++;
      navigate('/login');
    }
    if (index === 4) {
        console.log(inputs)
        // signupUser(inputs).then((res) => {
        //     console.log(res)
            navigate('/')
        // })
    }
  }, [index]);

  const handleIndexBack = () => {
    setIndex(index - 1);
  };
  const handleIndexNext = () => {
    if(inputs[index]===''){
      alert('내용을 입력해주세요.')
    } else {
      setIndex(index + 1);
    }
  };


  const handleInput = (e:React.ChangeEvent<HTMLInputElement>) => {
    const {name, value} = e.target
    console.log(value)
    setInputs((prevInputs) => {
      const updatedInputs = [...prevInputs]
      updatedInputs[index] = value
      return updatedInputs
    })
  }
  const handleNicknameCheck = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      const res = nicknameCheck( inputs[index] )

      if (res.available){
        alert('해당 닉네임을 사용할 수 있습니다.')
      } else {
        alert('해당 닉네임이 이미 존재합니다.')
      }
    } catch (error) {
      console.log('Error:', error)
    }
  };

  return (
    <>
      <span className="material-icons-outlined" onClick={handleIndexBack}>
        뒤로
      </span>
        {index===0 && <><input type="checkbox" onChange={handleInput}/>약관 동의 1</>} 
        {index===1 &&
         <>
         <form onSubmit={handleNicknameCheck}>
          <input type="text" name="nickname" onChange={ handleInput } value={inputs[index]}/>
          <button type='submit'>중복검사</button>
         </form>
         </>}
        {index===2 && <><input type="text" name="birthday" onChange={ handleInput } value={inputs[index]}/></>}
        {index===3 && <><input type="text" name="account" onChange={ handleInput } value={inputs[index]}/></>}
        <Button onClick={ handleIndexNext }>다음</Button>
    </>
  );
};

export default Signup;

// type PropsType = {
//     props:string;
// }
// const ChildComponent = ({props}:PropsType) => {
//     const [agree, setAgree] = useRecoilState(agreeState)
//     const [nickname, setNickname] = useRecoilState(nicknameState)
//     const [birthday, setBirthday] = useRecoilState(birthdayState)
//     const [account, setAccount] = useRecoilState(accountState)
//     const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
//         const { name, value} = e.target;
//         setNickname(value)
//     };
//     const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
//         e.preventDefault();
        
//     }

//     switch (props) {
//     case 'agree':
//       return <div>agree</div>;
//     case 'nickname':
//       return (
//         <>
//         <form onSubmit={handleSubmit}>
//           <input name="nickname"type="text" onChange={ handleInputChange } required />
//           {nickname}
//             <Button type="submit">다음</Button>

//         </form>
//         </>
//       );
//     case 'birthday':
//       return (
//         <>
//           {/* <input type="text" onChange={onChange} value={inputs.birthday} /> */}
//           {birthday}
//         </>
//       );
//     case 'account':
//       return (
//         <>
//           {/* <input type="text" onChange={onChange} value={inputs.account} /> */}
//           {account}
//         </>
//       );
//   }
// };
