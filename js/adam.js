// Quizvragen voor Prophet Musa (voorbeeld)
const quizQuestions = [
    {
       question: "Wie is de eerste mens die Allah heeft geschapen?",
        options: ["Mohammed", "Musa", "Noeh", "Adem"],
        correct: "Adem" 
    },
    {
        question: "Allah geeft Adem op welk verschillende klei gemaakt?",
        options: ["zoute en zout water", "klei van verschillende kleuren, harde en zachte klei", "alle soorten planten ", "huid van dieren"],
        correct: "klei van verschillende kleuren, harde en zachte klei"
    },
    {
        question: "Op welk dag werd Adam gemaakt?",
        options: ["Woensdag", "vrijdag", "Maandag", "Zaterdag"],
        correct: "vrijdag"
    },
    {
        question: "Waarom werd Adam en Hawa weg van paradijs?",
        options: ["omdat ze van een boom hebben gegeten waarvan Allah heeft verboden", "omdat ze niet naar Allah geloofden", "omdat ze zichzelf verkeerd gedroegen", " omdat ze niet naar de engelen luisterden"],
        correct: "omdat ze van een boom hebben gegeten waarvan Allah heeft verboden"
    },
    {
        question: "Waarom weigert iblis zich neer te knielen voor Adam?",
        options: ["omdat die mooier is dan Adam", "omdat hij zichzelf beter achtte dan een mens", "omdat hij de namen van alles weet", " omdat hij slimmer is dan Adam"],
        correct: "omdat hij zichzelf beter achtte dan een mens"
    }
    
];


const totalQuestions = quizQuestions.length; // Totaal aantal vragen
let currentQuestion = 0;
let score = 0; // Hier houden we bij hoeveel vragen correct zijn


// Functie om progress naar backend te sturen
function saveProgress(score, prophetId) {

    const userId = localStorage.getItem("userId");
    if (!userId) {
        alert("Je moet eerst inloggen!");
        window.location.href = "login.html";
        return;
    }
    const progressPercentage = Math.round((score / totalQuestions) * 100);
    fetch(`http://localhost:8080/api/progress?userId=${userId}&prophetId=${prophetId}&progressPercentage=${progressPercentage}`, {
        method: "POST",
        headers: {
        "Content-Type": "application/json"
       }
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
        saveProgress(score,1); // call backend
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
