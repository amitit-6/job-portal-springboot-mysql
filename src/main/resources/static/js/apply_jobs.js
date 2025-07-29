window.addEventListener("DOMContentLoaded", () => {
    
                if (document.querySelector(".alert.success")) {
                    setTimeout(() => {
                        window.location.href = "/applicant/applicant_dashboard";
                    }, 3000);
                }

                if (document.querySelector(".alert.warning")) {
                    setTimeout(() => {
                        window.location.href = "/applicant/apply_jobs";
                    }, 3000);
                }

                const searchInput = document.getElementById("jobSearch");
                searchInput.addEventListener("input", () => {
                    const keyword = searchInput.value.toLowerCase();
                    document.querySelectorAll(".job-card").forEach(card => {
                        const title = card.querySelector(".job-title").textContent.toLowerCase();
                        const company = card.querySelector(".job-detail span").textContent.toLowerCase();
                        card.style.display = (title.includes(keyword) || company.includes(keyword)) ? "block" : "none";
                    });
                });
            });