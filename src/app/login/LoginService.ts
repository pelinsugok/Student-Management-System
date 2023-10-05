import axios from "axios";
import { BehaviorSubject } from "rxjs";

interface LoginRequest {
    username: string,
    password: string
}

interface LoginResponse {
    userId: number,
    username: string,
    authorities: string[]
}

let storage: Storage;
if (typeof window !== "undefined") {
    storage = window.sessionStorage as Storage;
} else {
    storage = {
        getItem: () => null,
        setItem: () => {
        },
        removeItem: () => {
        },
        length: 0,
        clear: () => {
        },
        key: (index: number) => '',
    } as Storage;
}
const userSubject = new BehaviorSubject<LoginResponse | null>(
    typeof window !== "undefined" ?
        JSON.parse(storage.getItem("current-user") || "null") :
        null
);

const AuthService = {
    currentUser: userSubject.asObservable(),

    get currentUserValue() { return userSubject.value; },

    login: async (loginRequest: LoginRequest) => {
        const response = await axios.post<LoginResponse>(
            "/api/login",
            loginRequest,
            {
                headers: { "Content-Type": "application/json" },
            }
        );
        const loginResponse = response.data;
        userSubject.next(loginResponse);
        storage.setItem("current-user", JSON.stringify(loginResponse));
    },

    logout: () => {
        userSubject.next(null);
        storage.removeItem("current-user");
    },

    setRememberMe: (value: boolean) => {
        storage = value ? localStorage : sessionStorage;
    }
};

export default AuthService;
