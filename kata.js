const userList = [];
const pubList = [];
const wallList = [];

const currentDate = () => {
  const date = new Date();
  date.setHours(0);
  date.setMinutes(0);
  date.setSeconds(0);
  date.setMilliseconds(0);
  return date;
}

const registerUser = (name) => {
  if (name && userList.indexOf(name) < 0) {
    userList.push(name);
  }
}

const publishMessage = (user, message, timestamp) => {
  if (_isUser(user) && message) {
    const obj = { user: user, message: message, timestamp: timestamp };
    pubList.push({ ...obj });
    wallList.push({ wallName: user, ...obj, timeDifference: null });
  }
}

const viewTimeline = (currentUser, targetUser) => {
  const date = currentDate();
  const viewSelfsTimeline = _isUser(currentUser) && !_isUser(targetUser);
  const viewOthersTimeline = _isUser(currentUser) && _isUser(targetUser);
  let list = [];
  if (viewSelfsTimeline) {
    list = pubList
      .filter(x => x.user === currentUser)
      .map(x => {
        return ({
          message: x.message,
          timestamp: x.timestamp,
          timeDifference: _getTimeDifference(date, x.timestamp)
        });
      });
  } else if (viewOthersTimeline) {
    list = pubList
      .filter(x => x.user === targetUser)
      .map(x => {
        return ({
          message: x.message,
          timestamp: x.timestamp,
          timeDifference: _getTimeDifference(date, x.timestamp)
        });
      });
  }
  let output = '';
  list.reverse().forEach((msg, index) => {
    output += `${index > 0 ? '\n' : ''}${msg.message}${!msg.timeDifference ? '' : ' ' + msg.timeDifference}`;
  });
  return output;
}

const followFriend = (currentUser, targetUser) => {
  const date = currentDate();
  const isCurrentUser = _isUser(currentUser);
  const isTargetUser = _isUser(targetUser);
  if (isCurrentUser && isTargetUser) {
    pubList
      .filter(x => x.user === targetUser)
      .map(x => {
        return ({
          ...x,
          timeDifference: _getTimeDifference(date, x.timestamp)
        });
      })
      .forEach(x => {
        wallList.push({
          wallName: currentUser,
          ...x
        });
      });
  }
}

const viewWall = (currentUser) => {
  const isCurrentUser = _isUser(currentUser);
  let output = '';
  if (isCurrentUser) {
    const list = wallList.filter(x => x.wallName === currentUser);
    list.forEach((x, i) => {
      output += `${i > 0 ? '\n' : ''}${x.user} - ${x.message}${!x.timeDifference ? '' : ' ' + x.timeDifference}`
    });
  }
  return output;
}

const _isUser = (user) => {
  return userList.indexOf(user) >= 0;
}

const _getTimeDifference = (time1, time2) => {
  let timeDiff = '';
  if (time1 && time2) {
    const diff = _millisToMinutesAndSeconds(time2 - time1);
    const minutes = diff && diff[0] ? diff[0] : null;
    const seconds = diff && diff[1] ? diff[1] : null;
    if (!minutes && seconds) {
      if (seconds <= 30) {
        timeDiff = `(${seconds} ${seconds === 1 ? 'second' : 'seconds'} ago)`;
      } else if (seconds > 30) {
        timeDiff = `(1 minute ago)`;
      }
    } else if (minutes) {
      if (seconds <= 30) {
        timeDiff = `(${minutes} ${minutes === 1 ? 'minute' : 'minutes'} ago)`;
      } else if (seconds > 30) {
        timeDiff = `(${++minutes} minutes ago)`;
      }
    }
  }
  return !timeDiff ? null : timeDiff;
}

const _millisToMinutesAndSeconds = (millis) => {
  const minutes = Math.floor(millis / 60000);
  const seconds = ((millis % 60000) / 1000).toFixed(0);
  return [minutes, parseInt((seconds < 10 ? '0' : '') + seconds)];
}

module.exports = {
  currentDate,
  registerUser,
  publishMessage,
  viewTimeline,
  followFriend,
  viewWall
};
