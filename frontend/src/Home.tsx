import { Link } from "react-router-dom";

function Home() {
    return (
        <div className='link-bar'>
            <p>aaron vidoe chat</p>
            <nav>
                <Link to="/signin" className="home-links">Sign In</Link>
                <Link to="/signup" className="home-links">Sign Up</Link>
            </nav>
        </div >
    );
}

export default Home;
