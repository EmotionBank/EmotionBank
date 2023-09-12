// 리다이렉트 페이지
import { useNavigate } from "react-router-dom";
import { useState, useEffect } from 'react'
import axios from "axios";
const Redirection = () => {
    const [ 회원가입여부, set회원가입여부 ] = useState(false)
    const code = window.location.search;
    const navigate = useNavigate();

    useEffect(() => {
        console.log(code)
        // 서버로 post 요청 후 받은 응답에서 닉네임이 없으면 회원가입 페이지로 navigate
        
        // 닉네임 있으면 메인 페이지로 navigate
        navigate('/')

    }, [])
    return(
        <div>로그인 중입니다.</div>
    )
}

export default Redirection;