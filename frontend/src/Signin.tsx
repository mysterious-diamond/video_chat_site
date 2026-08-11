import Linkbar from './linkbar';

function Signin() {
    return (
        <div>
            <Linkbar />

            <div className="signin-form-div">
                <h1>Sign in to start calling!</h1>
                <form className="signin-form">
                    <p>Username : <span><input type="text"></input></span></p>
                    <p>Password : <span><input type="password"></input></span></p>

                    <button type="submit">Sign In</button>
                </form>
            </div>
        </div>
    )
}

export default Signin;
