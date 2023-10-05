'use client'
import React, { useState } from "react";
import AuthService from "@/app/login/LoginService";
import { useRouter } from "next/navigation";
import { Button, TextField, Typography, Box } from "@mui/material";
import { Visibility, VisibilityOff } from "@mui/icons-material";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export default function LoginPage() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [showPassword, setShowPassword] = useState(false);
    const router = useRouter();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            await AuthService.login({ username, password });
            toast.success("Giriş başarılı");
            setTimeout(() => {
                router.push("/anasayfa");
            }, 1000);
        } catch (error) {
            if (error.response && error.response.status === 401) {
                toast.error("Kullanıcı adı veya şifre hatalı");
            } else {
                toast.error("Kullanıcı adı veya şifre hatalı");
            }
        }
    };

    return (
        <div>
            <Typography variant="h4" component="h2">
                Giriş Yap
            </Typography>
            <form onSubmit={handleLogin}>
                <div>
                    <TextField
                        label="Kullanıcı Adı"
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                        fullWidth
                    />
                </div>
                <div>
                    <TextField
                        label="Şifre"
                        type={showPassword ? "text" : "password"}
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                        fullWidth
                        InputProps={{
                            endAdornment: (
                                <Box
                                    onClick={() => setShowPassword(!showPassword)}
                                    sx={{ cursor: "pointer" }}
                                >
                                    {showPassword ? <VisibilityOff /> : <Visibility />}
                                </Box>
                            ),
                        }}
                    />
                </div>

                <div>
                    <Button type="submit" variant="contained" color="primary">
                        Giriş Yap
                    </Button>
                </div>
            </form>
            <ToastContainer
                position="top-right"
                autoClose={5000}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
            />
        </div>
    );
}
