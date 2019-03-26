import React from 'react';

const UserInfo = ({
  user
}) => {
  if (user === null) {
    return null
  }
  return (
    <div id="userinfo">
      <p>Logged in as: { user.username }</p>
    </div>
  )
}

export default UserInfo