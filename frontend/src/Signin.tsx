function Signin() {
    return (
        <div className="signin-form-div">
            <h1>Login to start calling!</h1>
            <form className="signin-form">
                <p>Username : <span><input type="text"></input></span></p>
                <p>Password : <span><input type="password"></input></span></p>

                <button type="submit">Login</button>
            </form>
        </div>
    )
}

export default Signin;
