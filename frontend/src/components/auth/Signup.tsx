import type React from 'react';
import Linkbar from '../linkbar';
import { userApi } from '../../api/user';

function SignUp() {
    const handleSubmit = async (formData: FormData) => {
        const name = formData.get("name") as string;
        const nickname = formData.get("nickname") as string;
        const password = formData.get("password") as string;

        userApi.signup(name, nickname, password);
    }

    return (
        <div>
            <Linkbar />

            <div className="signup-form-div">
                <h1>make accoutn for vidoe chat</h1>
                <form className="signup-form" action={handleSubmit}>
                    <p>Username : <span><input name='name' type="text"></input></span></p>
                    <p>Nickname (optional) : <span><input name='nickname' type='text' /></span></p>
                    <p>Password : <span><input name='password' type="password"></input></span></p>

                    <button type="submit">Sign Up</button>
                </form>
            </div>
        </div >
    );
}

export default SignUp;
