import { Link } from 'react-router-dom';

function Linkbar() {
    return (
        <div className='link-bar'>
            <Link to="/" className="home-links">aaron vidoe chat</Link>
            <nav>
                <Link to="/signin" className="home-links">Signin</Link>
                <Link to="/signup" className="home-links">Signup</Link>
            </nav>
        </div >
    );
}

export default Linkbar;
