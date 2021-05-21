const kata = require("./kata.js");
const alice = 'Alice';
const bob = 'Bob';
const charlie = 'Charlie';

test("Test - Feature: Publishing", () => {
  const aliceMessage = 'I love the weather today.';
  kata.registerUser(alice);
  const date = kata.currentDate();
  kata.publishMessage(alice, aliceMessage, date);
  expect(kata.viewTimeline(alice)).toBe('I love the weather today.');
});

test("Test - Feature: Timeline", () => {
  const bobMessageList = ['Darn! We lost!', 'Good game though.'];
  const expected = 'Good game though. (1 minute ago)\nDarn! We lost! (2 minutes ago)';
  kata.registerUser(bob);
  bobMessageList.forEach((bobsMessage, index) => {
    const date = kata.currentDate();
    date.setMinutes(bobMessageList.length - index);
    kata.publishMessage(bob, bobsMessage, date);
  });

  expect(kata.viewTimeline(alice, bob)).toBe(expected);
});

test("Test - Feature: Following", () => {
  const charlieMessage = 'I\'m in New York today! Anyone wants to have a coffee?';
  const expected = `
  Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)
  Bob - Good game though. (1 minute ago)
  Bob - Darn! We lost! (2 minutes ago)
  Alice - I love the weather today (5 minutes ago) 
  `;
  const date = kata.currentDate();
  date.setMinutes(5);
  date.setSeconds(15);
  kata.registerUser(charlie);
  kata.publishMessage(charlie, charlieMessage, date);
  kata.followFriend(charlie, alice);
  kata.followFriend(charlie, bob);
  expect(kata.viewWall(charlie)).toBe(expected);
});
