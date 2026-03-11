document.getElementById("loginForm").addEventListener("submit", async function(e) {
    e.preventDefault();


    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
try{
    const response = await fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({username,password})
    });


    const result = await response.json();
    console.log("Login response:", result);  // <--- dit laat zien wat er terugkomt

    if (response.ok) {
    localStorage.setItem("token", result.token);
    localStorage.setItem("userId", result.userId); // <— hier wordt userId opgeslagen
    console.log("userId opgeslagen in localStorage:", localStorage.getItem("userId"));
    alert("Login succesvol!");
    window.location.href = "dashboard.html";
    } else {
        alert("Login mislukt: " + result.message);
    }
} catch (err) {
    console.error("Fout bij login:", err);
    alert("Er is een fout opgetreden tijdens het inloggen.");
}
}); 