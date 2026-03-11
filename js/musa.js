const quizQuestions = [
    {
       question: "Wat was de vreeslijk bevel van farao?",
       options: ["alle meisjes moesten worden gedood", "alle pasgeboren babyjongens van israëlieten doden", "alle israëlieten doden", "enekl vrouwen moesten worden gedood"],
       correct: "alle pasgeboren babyjongens van israëlieten doden" 
    },
    {
       question: "wat heeft farao gezien in zijn droom waarvan hij bang voor was?",
       options: ["iemand die hem van zijn troon zou stoten", "dat al zijn kinderen dood zouden gaan", "terwijl hij slaapt, zal iamand hem met een mes neesteken ", "iamand gaat hem vergiftigen"],
       correct: "iemand die hem van zijn troon zou stoten"
    },
    {
       question: "Waar werd Musa opgevoegd?",
       options: ["in een bouderij", "in een bos", "bij zijn oom", "in paleis van farao"],
       correct: "in paleis van farao"
    },
    {
       question: "Wat heeft Allah gevraagt aan Musa?",
       options: ["neem de troon van farao", "vorm een leger om de Egyptenaren te doden ", "ga terug naar Egypte en bevrijd mijn volk uit de slavernij", " ga terug naar Egypte en dood farao"],
       correct: "ga terug naar Egypte en bevrijd mijn volk uit de slavernij"
    },
    {
       question: "Waar vluchte Musa en zijn volk?",
       options: ["Arabische Zee", "Rode Zee", "Perzische Zee", " Zwarte Zee"],
       correct: "Rode Zee"
    }
];

const totalQuestions = quizQuestions.length;
let currentQuestion = 0;
let score = 0;
const userId = localStorage.getItem("userId");
const prophetId = 3; // Musa


// Haal bestaande progress op bij pagina laden
async function loadProgress() {
    try {
        const response = await fetch(`http://localhost:8080/api/progress?userId=${userId}&prophetId=${prophetId}`);
        if (response.ok) {
            const data = await response.json();
            console.log("GET progress data:", data);

            const progressBar = document.getElementById("progressBar");
            if (progressBar) {
                progressBar.style.width = (data.progressPercentage || 0) + "%";
                progressBar.textContent = `${data.progressPercentage || 0}%`; // optioneel, toont percentage op de balk
            }

            // Zet score en currentQuestion op basis van progress
            score = Math.round((data.progressPercentage / 100) * totalQuestions);
            currentQuestion = score;
        }
    } catch (err) {
        console.error("Fout bij ophalen progress:", err);
    }
}

function showAudio() {
    document.getElementById("audioSection").style.display = "block";
}

function showVideo() {
    document.getElementById("videoSection").style.display = "block";
}

function showQuiz() {
    document.getElementById("quizSection").style.display = "block";
    loadProgress(); // Zorg ervoor dat progress wordt geladen voordat de quiz begint
    showQuestion();
}

function showQuestion() {
    const container = document.getElementById("quizContainer");
    container.innerHTML = "";

    if (currentQuestion < totalQuestions) {
        const q = quizQuestions[currentQuestion];
        const p = document.createElement("p");
        p.textContent = q.question;
        p.style.fontWeight = "bold";
        container.appendChild(p);

        q.options.forEach(opt => {
            const btn = document.createElement("button");
            btn.textContent = opt;
            btn.style.margin = "5px";
            btn.onclick = () => checkAnswer(opt);
            container.appendChild(btn);
        });
    } else {
        container.innerHTML = `<h4>Quiz voltooid! Je score: ${score}/${totalQuestions}</h4>`;
        saveProgress(score, prophetId);
    }
}

function checkAnswer(selected) {
    const q = quizQuestions[currentQuestion];
    if (selected === q.correct) {
        score++;
    }
    currentQuestion++;
    showQuestion();
}

function saveProgress(score) {
    const progressPercentage = Math.round((score / totalQuestions) * 100);
    fetch(`http://localhost:8080/api/progress?userId=${userId}&prophetId=${prophetId}&progressPercentage=${progressPercentage}`, {
        method: "POST"
    })
    .then(res => res.json())
    .then(data => {
        const progressBar = document.getElementById("progressBar");
        if (progressBar) {
            progressBar.style.width = data.progressPercentage + "%";
            progressBar.textContent = `${data.progressPercentage}%`;
        }
    })
    .catch(err => console.error(err));
}