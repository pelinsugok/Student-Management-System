'use client'

import React, { useState } from "react";
import AuthService from "@/app/login/LoginService";
import { useRouter } from "next/navigation";
import { Button, TextField, Typography, Box, Snackbar } from "@mui/material";
import MuiAlert from "@mui/material/Alert";
import { Visibility, VisibilityOff } from "@mui/icons-material";

export default function LoginPage() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [showPassword, setShowPassword] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");
    const [open, setOpen] = useState(false);
    const router = useRouter();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            await AuthService.login({ username, password });
            router.push("/anasayfa");
        } catch (error) {
            if (error.response && error.response.status === 401) {
                setErrorMessage("Kullanıcı adı veya şifre yanlış.");
            } else {
                setErrorMessage("Bir hata oluştu. Lütfen tekrar deneyin.");
            }
            setOpen(true);
        }
    };

    const handleSnackbarClose = () => {
        setOpen(false);
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
                {errorMessage && (
                    <Box mt={2}>
                        <MuiAlert severity="error">{errorMessage}</MuiAlert>
                    </Box>
                )}
                <div>
                    <Button type="submit" variant="contained" color="primary">
                        Giriş Yap
                    </Button>
                </div>
            </form>
            <Snackbar
                open={open}
                autoHideDuration={6000}
                onClose={handleSnackbarClose}
            >
                <MuiAlert
                    elevation={6}
                    variant="filled"
                    severity="error"
                    onClose={handleSnackbarClose}
                >
                    {errorMessage}
                </MuiAlert>
            </Snackbar>
        </div>
    );
}
