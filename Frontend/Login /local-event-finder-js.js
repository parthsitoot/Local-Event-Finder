document.addEventListener('DOMContentLoaded', function() {
    // State management
    let isLogin = true;
    
    // DOM elements
    const formTitle = document.getElementById('form-title');
    const nameGroup = document.getElementById('name-group');
    const forgotPassword = document.getElementById('forgot-password');
    const rememberMeContainer = document.getElementById('remember-me-container');
    const submitButton = document.getElementById('submit-button');
    const toggleText = document.getElementById('toggle-text');
    const toggleModeButton = document.getElementById('toggle-mode-button');
    const socialLogin = document.getElementById('social-login');
    const form = document.getElementById('auth-form');
    const errorContainer = document.getElementById('error-container');
    const errorMessage = document.getElementById('error-message');
    const passwordInput = document.getElementById('password');
    
    // Toggle between login and register
    function toggleMode() {
        isLogin = !isLogin;
        
        // Update UI based on mode
        if (isLogin) {
            formTitle.textContent = 'Welcome Back';
            nameGroup.style.display = 'none';
            forgotPassword.style.display = 'block';
            rememberMeContainer.style.display = 'flex';
            submitButton.textContent = 'Sign In';
            toggleText.textContent = "Don't have an account?";
            toggleModeButton.textContent = 'Sign up';
            socialLogin.style.display = 'block';
            passwordInput.placeholder = 'Your password';
        } else {
            formTitle.textContent = 'Create Account';
            nameGroup.style.display = 'block';
            forgotPassword.style.display = 'none';
            rememberMeContainer.style.display = 'none';
            submitButton.textContent = 'Create Account';
            toggleText.textContent = 'Already have an account?';
            toggleModeButton.textContent = 'Sign in';
            socialLogin.style.display = 'none';
            passwordInput.placeholder = 'Create a password';
        }
        
        // Clear any errors
        hideError();
    }
    
    function showError(message) {
        errorMessage.textContent = message;
        errorContainer.style.display = 'block';
    }
    
    function hideError() {
        errorContainer.style.display = 'none';
    }
    
    // Handle form submission
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        hideError();
        
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const name = document.getElementById('name').value;
        const rememberMe = document.getElementById('remember-me').checked;
        
        // Basic validation
        if ((!isLogin && !name) || !email || !password) {
            showError('Please fill in all required fields');
            return;
        }
        
        // Email validation
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            showError('Please enter a valid email address');
            return;
        }
        
        // Mock successful authentication
        console.log('Form submitted:', { 
            email, 
            password, 
            name: !isLogin ? name : undefined,
            rememberMe: isLogin ? rememberMe : undefined
        });
        
        alert(`${isLogin ? 'Login' : 'Registration'} successful!`);
    });
    
    // Event listeners
    toggleModeButton.addEventListener('click', function(e) {
        e.preventDefault();
        toggleMode();
    });
});
