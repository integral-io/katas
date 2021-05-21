const hello = require("./kata.js");

test("the tests run", () => {
  expect(hello()).toBe("hello world!");
});
