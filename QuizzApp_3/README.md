---

# 🎉 QuizzApp_3 🚀

Welcome to **QuizzApp_3**! This is the latest and greatest version of my Java console-based quiz application, now with even more exciting features! Version 3 comes with advanced file I/O capabilities for storing quiz data, smart question management, and a vibrant, user-friendly interface. 🎨

## 🆕 What's New in Version 3?

### 📂 File I/O Integration

In this version, I've introduced file I/O to handle quiz questions, options, and answers. All the quiz data is neatly stored in a `content.txt` file. The `QuestionFileParser` class reads this file and loads the questions into the application, making it a breeze to manage and update the quiz content.

### 🧠 Smart Question Management

Managing questions has never been easier! In this version, questions are categorized in multiple ways:

- **By Category:** Questions are grouped into categories like Java, Data Structures, Geography, and more—10 categories in total!
- **By Difficulty Level:** Questions are also sorted by difficulty: Easy, Medium, Hard, and Very Hard.
- **Random Selection:** For those looking for a bit of randomness, you can choose questions at random, ignoring both category and difficulty level. 🎲

### ✨ Enhanced Question Formatting

Each question in the `content.txt` file is stored in a well-organized format:
```
**Question:** What is the capital of France?
**Options:**
A) Paris
B) London
C) Berlin
D) Madrid
**Correct Answer:** A) Paris
**Category:** Geography
**Difficulty Level:** Medium
```

The `Question.java` class in the `QuizzApp_3.Formatting` package ensures these questions are displayed in a colorful and structured way, making the quiz more engaging and easy on the eyes.

### 🌈 Colorful CLI Interface

The `Displayer.java` file, part of the `QuizzApp_3.Formatting` package, brings the quiz to life with a vibrant CLI interface. Questions, options, and other details are displayed in different colors, making your quiz experience visually appealing and fun!

### 🏆 Quiz Functionality

The quiz is designed to be both challenging and entertaining:

- You'll receive a set of 10 questions from any chosen category.
- Quiz rules are displayed at the start, so you know exactly what to expect.
- **Lifelines:** I've added a few lifelines to help you out, but remember—you can only use each one once per quiz!
  - Reduce Options
  - Show Correct Answer
  - Give a Hint
  - Call a Friend (just for fun—this one’s fake! 😄)
- Once the quiz is over, your score is calculated based on the number of correct answers. How well did you do? 🎯

## ⚙️ Installation & How to Run

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/MeetkumarChavda/Java-projects.git
   ```
2. **Compile the Java Files:**
   ```bash
   javac -d bin -sourcepath src src/QuizzApp_3/Formatting/Displayer.java
   javac -d bin -sourcepath src src/QuizzApp_3/Application/*.java
   ```
3. **Run the Application:**
   ```bash
   java -cp bin QuizzApp_3.Application.QuizApp
   ```

## 🤝 Contributing

Interested in contributing to QuizzApp_3? Fork the repository, make your changes, and submit a pull request. I'm always open to new ideas and improvements!

## 📄 PDF of Output

Want to see what the quiz looks like in action? Check out the sample output in the PDF below:

[PDF file](<Output/QuizApp_3 Output.pdf>)

## 📜 License

This project is licensed under the MIT License. Feel free to use, modify, and distribute the code as per the terms of the license.

---

