import { userApi } from '../../api/user';
import { storeJwtToken } from '../../utils/jwtToken';
import Linkbar from '../linkbar';

function SignIn() {
    const handleSubmit = async (formData: FormData) => {
        const name = formData.get("name") as string;
        const password = formData.get("password") as string;

        const token = await userApi.signin(name, password);
        storeJwtToken(token);
    }

    return (
        <div>
            <Linkbar />

            <div className="signin-form-div">
                <h1>Sign in to start calling!</h1>
                <form className="signin-form" action={handleSubmit}>
                    <p>Username : <span><input type="text" name='name'></input></span></p>
                    <p>Password : <span><input type="password" name='password'></input></span></p>

                    <button type="submit">Sign In</button>
                </form>
            </div>
        </div>
    )
}

export default SignIn;
