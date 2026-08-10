import { Link } from 'react-router-dom';

function SignUp() {
    return (
        <div>
            <div className='link-bar'>
                <p>aaron vidoe chat</p>
                <nav>
                    <Link to="/signin" className="home-links">Sign In</Link>
                    <Link to="/signup" className="home-links">Sign Up</Link>
                </nav>
            </div >

            <div className="signup-form-div">
                <h1>make accoutn for vidoe chat</h1>
                <form className="signup-form">
                    <p>Username : <span><input type="text"></input></span></p>
                    <p>Nickname (optional) : <span><input type='text' /></span></p>
                    <p>Password : <span><input type="password"></input></span></p>

                    <button type="submit">Sign Up</button>
                </form>
            </div>
        </div>
    );
}

export default SignUp;
