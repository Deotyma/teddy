const messages = {
    home: {
        find:"Find",
        toy: "a perfect toy",
        for: "for your child.",
        description: "Here everyone can find, exchange, buy, sell or donate toys, books and educational material for children with special needs and not only.",
        register: "Register",
        new: "A new annonces"
    },
    userForm:{
        firstName:"First name",
        lastName:"Last name",
        email:"Email",
        password:"Password",
        repeatPassword:"Repeat password",
        address:"Address",
        zipCode:"Zip code",
        town:"Town"
    },
    errorMessages: {
        required: 'This field is required.',
        minLength: 'The password must be at least 8 characters long.',
        maxLength: 'The password cannot exceed 20 characters.',
        hasUppercase: 'The password must contain at least one uppercase letter.',
        hasLowercase: 'The password must contain at least one lowercase letter.',
        hasNumber: 'The password must contain at least one number.',
        hasSymbol: 'The password must contain at least one symbol.',
      },
      annonce:{
        see: 'See'
      }, 
      formAnnonce:{
        create:'Create your annonce',
        title:'Title',
        text: 'Annonce text',
        photo: 'Photo',
        photoInstructions:"Must be JPEG, PNG or GIF, and not exceed 1MB."
      }
} 

export default messages