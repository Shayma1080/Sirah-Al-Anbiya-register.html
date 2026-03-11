const userId = localStorage.getItem("userId");

async function getTotalProgress() {
    try {
        // lijst van prophetIds
        const prophetIds = [1,2,3,4,5,6,7,8,9,10]; // 1=Adam, 2=Noeh, 3=Musa, etc.
        let total = 0;

        for (const id of prophetIds) {
            const response = await fetch(`http://localhost:8080/api/progress?userId=1&prophetId=${id}`);
            if (response.ok) {
                const data = await response.json();
                total += data.progressPercentage || 0;
            }
        }

        // Gemiddelde progress berekenen (optioneel)
        const average = Math.round(total / prophetIds.length);

        document.getElementById("totalProgressResult").textContent = 
            `Totale progress: ${average}%`;

    } catch (err) {
        console.error(err);
        document.getElementById("totalProgressResult").textContent = "Fout bij ophalen progress";
    }
}

document.getElementById("totalProgressBtn").addEventListener("click", getTotalProgress);