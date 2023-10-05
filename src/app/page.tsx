"use client"

import {useRouter} from "next/navigation";
import {useEffect} from "react";
import LoginService from "@/app/login/LoginService";

export default function Home() {
  const router = useRouter();

  useEffect(() => {
    if (LoginService.currentUserValue) {
      router.push("/anasayfa");
    } else {
      router.push('/login');
    }
  }, []);

  return (
      <div></div>
  );
}