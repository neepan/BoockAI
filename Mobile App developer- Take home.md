## **Mobile App Developer \- Take-home assignment** 

Boock Android Take-Home — Reader Mini

### **Timebox**

Please spend no more than 1 working day (max 8 hours). If you run out of time, stop there and tell us what you would do next.

### **Scenario**

Boock is a consumer reading app. We want a lightweight Android prototype that lets a user browse a small library, open a title, read sample content, and return later without losing their place.

### **What we provide**

* A lightweight starter repo with Kotlin and basic Android app setup

* A small JSON bundle with 8–12 titles and 1 sample chapter/article per title

* Optional basic brand colors or mockups

### **Tech expectations**

* Build for Android in Kotlin

* Jetpack Compose is the preferred/default UI approach

* Use stable, production-appropriate libraries

* No real backend, auth, admin panel, or CMS is needed

### **What to build**

#### **1\) Home / Library**

Build a clean consumer-facing home screen that:

* shows the provided titles in a list or grid

* shows title, author, cover or placeholder, and reading status if started

* supports search by title or author

* includes a loading state and at least one non-happy state such as empty or error

#### **2\) Book Detail**

Build a detail screen that:

* shows title, author, summary, and a primary CTA

* changes the CTA between Start Reading and Continue Reading

* lets the user save/remove a title from their library or favorites

#### **3\) Reader**

Build a reader screen that:

* displays the provided sample text

* remembers the last-read position or a simple progress value in a reasonable way

* supports at least 3 text-size options

* supports dark mode

#### **4\) Local persistence**

Persist locally so that:

* saved titles survive app restarts

* last-read item and reading progress survive app restarts

#### **5\) Engineering quality**

We expect:

* an app structure that could scale beyond this exercise

* at least 2 meaningful automated tests

  * 1 logic/unit test

  * 1 user-visible behavior test (UI, instrumentation, or screenshot test)

* accessible basics such as readable text, clear labels, sensible touch targets, and behavior that still works with larger system font sizes

* clean handling of the happy path and at least one error/edge case

#### **6\) Large-screen thinking**

Do one of these:

* implement 1 tablet/large-screen improvement, or

* explain in your notes how you would adapt the app for tablets/foldables in production

### **AI usage**

You may use any AI tools you normally work with, including tools such as Codex, Claude Opus/Claude Code, or ChatGPT. We care about how you use AI, not whether you use it.

Please include an AI\_USAGE.md file with:

* tools used

* 3–6 representative prompts/tasks

* where AI materially helped

* one AI suggestion you rejected or corrected, and why

* how you verified correctness, maintainability, and quality

You are responsible for everything you submit.

### **Submission**

Please send:

* source code

* README.md with setup steps, assumptions, and known gaps

* DECISIONS.md with:

  * architecture choices

  * tradeoffs

  * 2–3 things you had to figure out on your own and how you resolved them

  * what you would improve with 2 more days

* AI\_USAGE.md

* optional 3–5 minute walkthrough video

Bullet points are completely fine. We value clarity over long documents.

### **How we’ll evaluate**

We are not looking for the biggest feature set. We are looking for:

* professional quality in the core flow

* good Android engineering judgment

* thoughtful tradeoffs within a 1-day timebox

* self-direction and resourcefulness

* responsible use of AI tools

* code that another engineer would feel comfortable extending

### **Important note**

Please make reasonable assumptions and document them instead of waiting for detailed implementation guidance.

---

