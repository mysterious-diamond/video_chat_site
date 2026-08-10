import { Route, Routes } from 'react-router-dom'
import Signin from './Signin';
import Home from './Home';

function App() {
    return (
        <Routes>
            <Route path='/' element={<Home />} />
            <Route path='/signin' element={<Signin />} />
        </Routes>
    );
}

export default App
