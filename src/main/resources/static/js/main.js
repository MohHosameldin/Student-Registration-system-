// Course Registration System - Main JavaScript
// Location: src/main/resources/static/js/main.js

document.addEventListener('DOMContentLoaded', function() {
    // Add racing stripe to all pages
    addRacingStripe();
    
    // Initialize animations
    initAnimations();
    
    // Form validations
    initFormValidations();
    
    // Confirmation dialogs
    initConfirmationDialogs();
    
    // Auto-hide alerts
    autoHideAlerts();
    
    // Table row animations
    initTableAnimations();
    
    // Add Ferrari sound effect on button clicks (optional)
    // initSoundEffects();
});

// Add Ferrari racing stripe decoration
function addRacingStripe() {
    const stripe = document.createElement('div');
    stripe.className = 'racing-stripe';
    document.body.insertBefore(stripe, document.body.firstChild);
}

// Initialize scroll and entrance animations
function initAnimations() {
    const sections = document.querySelectorAll('.section');
    
    // Add entrance animation class
    sections.forEach((section, index) => {
        section.style.opacity = '0';
        section.style.transform = 'translateY(30px)';
        
        setTimeout(() => {
            section.style.transition = 'all 0.6s ease';
            section.style.opacity = '1';
            section.style.transform = 'translateY(0)';
        }, index * 150);
    });
    
    // Intersection Observer for scroll animations
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.style.opacity = '1';
                entry.target.style.transform = 'translateY(0)';
            }
        });
    }, {
        threshold: 0.1
    });
    
    sections.forEach(section => observer.observe(section));
}

// Form validation
function initFormValidations() {
    const forms = document.querySelectorAll('form');
    
    forms.forEach(form => {
        form.addEventListener('submit', function(e) {
            const inputs = form.querySelectorAll('input[required], select[required]');
            let isValid = true;
            
            inputs.forEach(input => {
                if (!input.value.trim()) {
                    isValid = false;
                    input.style.borderColor = 'var(--ferrari-red)';
                    input.style.animation = 'shake 0.5s';
                    
                    setTimeout(() => {
                        input.style.animation = '';
                    }, 500);
                } else {
                    input.style.borderColor = '#ddd';
                }
            });
            
            if (!isValid) {
                e.preventDefault();
                showNotification('Please fill in all required fields', 'error');
            } else {
                // Add loading state to submit button
                const submitBtn = form.querySelector('button[type="submit"]');
                if (submitBtn && !form.hasAttribute('data-no-loading')) {
                    submitBtn.disabled = true;
                    submitBtn.innerHTML = '<span class="loading"></span> Processing...';
                }
            }
        });
    });
}

// Confirmation dialogs for critical actions
function initConfirmationDialogs() {
    // Approve confirmations
    const approveButtons = document.querySelectorAll('form[action*="/approve"] button');
    approveButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            if (!confirm('Are you sure you want to APPROVE this enrollment?')) {
                e.preventDefault();
            }
        });
    });
    
    // Reject confirmations
    const rejectButtons = document.querySelectorAll('form[action*="/reject"] button');
    rejectButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            if (!confirm('Are you sure you want to REJECT this enrollment?')) {
                e.preventDefault();
            }
        });
    });
    
    // Grade submission confirmations
    const gradeButtons = document.querySelectorAll('form[action*="/grade"] button');
    gradeButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            const form = button.closest('form');
            const grade = form.querySelector('input[name="grade"]').value;
            const passed = form.querySelector('select[name="passed"]').value;
            
            if (!grade) {
                e.preventDefault();
                showNotification('Please enter a grade', 'error');
                return;
            }
            
            if (!confirm(`Submit grade ${grade} (${passed === 'true' ? 'PASSED' : 'FAILED'})?`)) {
                e.preventDefault();
            }
        });
    });
}

// Auto-hide alerts after 5 seconds
function autoHideAlerts() {
    const alerts = document.querySelectorAll('.alert');
    
    alerts.forEach(alert => {
        setTimeout(() => {
            alert.style.transition = 'all 0.5s ease';
            alert.style.opacity = '0';
            alert.style.transform = 'translateX(100%)';
            
            setTimeout(() => {
                alert.remove();
            }, 500);
        }, 5000);
        
        // Add close button
        const closeBtn = document.createElement('span');
        closeBtn.innerHTML = '&times;';
        closeBtn.style.cssText = `
            float: right;
            font-size: 24px;
            font-weight: bold;
            cursor: pointer;
            margin-left: 15px;
        `;
        closeBtn.addEventListener('click', () => {
            alert.style.transition = 'all 0.3s ease';
            alert.style.opacity = '0';
            alert.style.transform = 'translateX(100%)';
            setTimeout(() => alert.remove(), 300);
        });
        alert.insertBefore(closeBtn, alert.firstChild);
    });
}

// Table row hover animations
function initTableAnimations() {
    const tableRows = document.querySelectorAll('tbody tr');
    
    tableRows.forEach((row, index) => {
        row.style.opacity = '0';
        row.style.transform = 'translateX(-20px)';
        
        setTimeout(() => {
            row.style.transition = 'all 0.4s ease';
            row.style.opacity = '1';
            row.style.transform = 'translateX(0)';
        }, index * 50);
    });
}

// Show notification (custom alert)
function showNotification(message, type = 'info') {
    const notification = document.createElement('div');
    notification.className = `alert alert-${type}`;
    notification.textContent = message;
    notification.style.cssText = `
        position: fixed;
        top: 80px;
        right: 20px;
        z-index: 10000;
        min-width: 300px;
        animation: slideIn 0.5s ease;
    `;
    
    document.body.appendChild(notification);
    
    setTimeout(() => {
        notification.style.transition = 'all 0.3s ease';
        notification.style.opacity = '0';
        notification.style.transform = 'translateX(100%)';
        setTimeout(() => notification.remove(), 300);
    }, 3000);
}

// Add shake animation CSS
const style = document.createElement('style');
style.textContent = `
    @keyframes shake {
        0%, 100% { transform: translateX(0); }
        10%, 30%, 50%, 70%, 90% { transform: translateX(-5px); }
        20%, 40%, 60%, 80% { transform: translateX(5px); }
    }
    
    @keyframes pulse {
        0% { transform: scale(1); }
        50% { transform: scale(1.05); }
        100% { transform: scale(1); }
    }
    
    .pulse {
        animation: pulse 0.5s ease;
    }
`;
document.head.appendChild(style);

// Add Ferrari-style button hover effects
function initButtonEffects() {
    const buttons = document.querySelectorAll('.btn');
    
    buttons.forEach(button => {
        button.addEventListener('mouseenter', function() {
            this.style.transform = 'translateY(-2px)';
        });
        
        button.addEventListener('mouseleave', function() {
            this.style.transform = 'translateY(0)';
        });
        
        button.addEventListener('mousedown', function() {
            this.classList.add('pulse');
        });
        
        button.addEventListener('animationend', function() {
            this.classList.remove('pulse');
        });
    });
}

initButtonEffects();

// Search/Filter functionality for tables (optional enhancement)
function initTableSearch() {
    const searchInput = document.createElement('input');
    searchInput.type = 'text';
    searchInput.placeholder = 'Search courses...';
    searchInput.className = 'table-search';
    searchInput.style.cssText = `
        width: 100%;
        max-width: 300px;
        padding: 10px 15px;
        margin-bottom: 15px;
        border: 2px solid #ddd;
        border-radius: 8px;
        font-size: 14px;
    `;
    
    const tables = document.querySelectorAll('table');
    tables.forEach(table => {
        const tableContainer = table.parentElement;
        tableContainer.insertBefore(searchInput.cloneNode(true), table);
        
        const search = tableContainer.querySelector('.table-search');
        search.addEventListener('input', function() {
            const filter = this.value.toLowerCase();
            const rows = table.querySelectorAll('tbody tr');
            
            rows.forEach(row => {
                const text = row.textContent.toLowerCase();
                row.style.display = text.includes(filter) ? '' : 'none';
            });
        });
    });
}

// Grade input validation
function initGradeValidation() {
    const gradeInputs = document.querySelectorAll('input[name="grade"]');
    
    gradeInputs.forEach(input => {
        input.addEventListener('input', function() {
            const value = parseFloat(this.value);
            
            if (value < 0 || value > 100) {
                this.style.borderColor = 'var(--ferrari-red)';
                showNotification('Grade must be between 0 and 100', 'error');
            } else {
                this.style.borderColor = 'var(--success-green)';
                
                // Auto-set passed/failed based on grade
                const passedSelect = this.closest('form').querySelector('select[name="passed"]');
                if (passedSelect) {
                    passedSelect.value = value >= 50 ? 'true' : 'false';
                }
            }
        });
    });
}

initGradeValidation();

// Add Ferrari engine sound effect (optional - commented out by default)
/*
function initSoundEffects() {
    const audioContext = new (window.AudioContext || window.webkitAudioContext)();
    
    function playRevSound() {
        const oscillator = audioContext.createOscillator();
        const gainNode = audioContext.createGain();
        
        oscillator.connect(gainNode);
        gainNode.connect(audioContext.destination);
        
        oscillator.frequency.setValueAtTime(200, audioContext.currentTime);
        oscillator.frequency.exponentialRampToValueAtTime(400, audioContext.currentTime + 0.1);
        
        gainNode.gain.setValueAtTime(0.3, audioContext.currentTime);
        gainNode.gain.exponentialRampToValueAtTime(0.01, audioContext.currentTime + 0.1);
        
        oscillator.start(audioContext.currentTime);
        oscillator.stop(audioContext.currentTime + 0.1);
    }
    
    const primaryButtons = document.querySelectorAll('.btn-primary');
    primaryButtons.forEach(button => {
        button.addEventListener('click', playRevSound);
    });
}
*/

// Console Ferrari ASCII Art
console.log(`
%c
███████╗███████╗██████╗ ██████╗  █████╗ ██████╗ ██╗
██╔════╝██╔════╝██╔══██╗██╔══██╗██╔══██╗██╔══██╗██║
█████╗  █████╗  ██████╔╝██████╔╝███████║██████╔╝██║
██╔══╝  ██╔══╝  ██╔══██╗██╔══██╗██╔══██║██╔══██╗██║
██║     ███████╗██║  ██║██║  ██║██║  ██║██║  ██║██║
╚═╝     ╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝
Course Registration System - Powered by Spring Boot
`, 'color: #DC0000; font-weight: bold;');

console.log('%cForza Ferrari! 🏎️💨', 'color: #FFF200; font-size: 20px; font-weight: bold;');