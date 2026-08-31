import { Route, Routes } from 'react-router-dom'
import Signin from './Signin';
import Home from './Home';
import SignUp from './Signup';

function App() {
    return (
        <Routes>
            <Route path='/' element={<Home />} />
            <Route path='/signin' element={<Signin />} />
            <Route path='/signup' element={<SignUp />} />
        </Routes>
    );
}

export default App
