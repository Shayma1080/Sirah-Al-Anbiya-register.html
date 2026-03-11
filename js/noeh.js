// Quizvragen voor Prophet Nuh (voorbeeld)
const quizQuestions = [
    {
       question: "Wat heeft Allah gevraagt aan Noeh?",
        options: ["de volk verwittigen dat ze Allah moeten aanbidden", "de volk roepen om samen een ark te bouwen", "de volk verwittigen dat ze zich moeten voorbereiden op de zondvloed", "de volk verwittigen om het land te verlaten"],
        correct: "de volk verwittigen dat ze Allah moeten aanbidden" 
    },
    {
        question: "Hoeveel jaar riep Profeet Noeh zijn volk op?",
        options: ["950 jaar", "100 jaar", "500 jaar", "200 jaar"],
        correct: "950 jaar"
    },
    {
        question: "Wat moest Profeet Noeh bouwen?",
        options: ["Een paleis", "Een ark", "Een toren", "Een huis"],
        correct: "Een ark"
    },
    {
        question: "Wat moest Profeet Noeh meenemen in zijn ark?",
        options: ["zijn zoon", "van elk dier een vrouwlijk en mannelijjk", "goud", " eten"],
        correct: "van elk dier een vrouwlijk en mannelijjk"
    }
    
];

const totalQuestions = quizQuestions.length; // Totaal aantal vragen
let currentQuestion = 0;
let score = 0; // Hier houden we bij hoeveel vragen correct zijn
const userId = localStorage.getItem("userId");

// Functie om progress naar backend te sturen
function saveProgress(score, prophetId) {
    const progressPercentage = Math.round((score / totalQuestions) * 100);
    fetch(`http://localhost:8080/api/progress?userId=${userId}&prophetId=${prophetId}&progressPercentage=${progressPercentage}`, {
        method: "POST"
    })
    .then(res => res.json())
    .then(data => {
        console.log(`Progress opgeslagen voor prophetId=${prophetId}:`, data);
        const progressBar = document.getElementById("progressBar");
        if (progressBar) progressBar.style.width = data.progressPercentage + "%";
    })
    .catch(err => console.error(err));
}


function showAudio() {
    document.getElementById("audioSection").style.display = "block";
}

function showVideo() {
    document.getElementById("videoSection").style.display = "block";
}


function showQuiz() {
    document.getElementById("quizSection").style.display = "block";
    showQuestion();
}

function showQuestion() {
    const container = document.getElementById("quizContainer");
    container.innerHTML = "";

    if (currentQuestion < quizQuestions.length) {
        const q = quizQuestions[currentQuestion];
        // Vraag tonen
        const p = document.createElement("p");
        p.textContent = q.question;
        p.style.fontWeight = "bold";
        container.appendChild(p);

        // Opties tonen
        q.options.forEach(opt => {
            const btn = document.createElement("button");
            btn.textContent = opt;
            btn.style.margin = "5px";
            btn.onclick = () => checkAnswer(opt);
            container.appendChild(btn);
        });
    } else {
        // Quizz voltooid
        container.innerHTML = `<h4>Quiz voltooid! Je score: ${score}/${quizQuestions.length}</h4>`;
        saveProgress(score,2); // call backend
    }
}

function checkAnswer(selected) {
    const q = quizQuestions[currentQuestion];
    if(selected === q.correct) {
        score++;
    }
    currentQuestion++;
    showQuestion();
}

localStorage.setItem("token", data.token);