import { Button } from '@components/common/Button/Button';
import { useState }from 'react';
import { useNavigate } from 'react-router-dom';
const Signup = () => {
    const [modeArray,setModeArray] = useState(['agree', 'nickname','birthday','account'])
    let [ index, setIndex ] = useState(0)
    const navigate = useNavigate();

    const handleIndexBack = () => {
        setIndex(index - 1)
    }
    const handleIndexNext = () => setIndex(index + 1)
    const ChildComponent = () => {
        switch (modeArray[index]) {
            case 'agree':
                return(
                    <>
                    agree
                    </>
                );
            case 'nickname':
                return(
                    <>
                    nickname
                    </>
                );
            case 'birthday':
                return(
                    <>
                    birthday
                    </>
                );
            case 'account':
                return(
                    <>
                    account
                    </>
                );
        
            default:
                break;
    }
    }
    return(
        <>
        <span className="material-icons-outlined" onClick={ handleIndexBack }>
            arrow_back
        </span>
        <ChildComponent />
        <Button onClick={ handleIndexNext }>다음</Button>
        </>
    )
}

export default Signup